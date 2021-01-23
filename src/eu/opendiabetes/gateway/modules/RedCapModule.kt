package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.database.HcpLink
import eu.opendiabetes.gateway.database.Participant
import eu.opendiabetes.gateway.database.ParticipationLink
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_HCP_STUDY_HCPS
import eu.opendiabetes.gateway.templates.respondConsentNotGivenTemplate
import eu.opendiabetes.gateway.templates.respondConsentTemplate
import eu.opendiabetes.gateway.utils.RedCapAPI
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.*


fun Application.redCapModule() {
    followupRedCapAPI = RedCapAPI(
        getStringProperty("gateway.redcap.apiUrl"),
        getStringProperty("gateway.redcap.followupToken")
    )
    val hcpRedCapAPI = RedCapAPI(
        getStringProperty("gateway.redcap.apiUrl"),
        getStringProperty("gateway.redcap.hcpToken")
    )
    routing {
        get("/survey") {
            val participant = call.participant
            when {
                participant == null -> call.respondRedirect("/")
                participant.surveyRecordId != null || participant.enrollmentType !in arrayOf(
                    EnrollmentType.ADULT_USING_DIYAPS, EnrollmentType.ADULT_NOT_USING_DIYAPS,
                    EnrollmentType.PARENT_USING_DIYAPS, EnrollmentType.PARENT_NOT_USING_DIYAPS
                ) -> call.respondRedirect("/todos")
                participant.followupSurveyRecordId == null -> {
                    val recordId = this@redCapModule.followupRedCapAPI.createRecord(participant.id, participant.enrollmentType.ordinal)
                    this@redCapModule.database.setFollowupSurveyRecordIdForParticipant(participant.id, recordId)
                    call.respondRedirect(this@redCapModule.followupRedCapAPI.exportSurveyQueueLink(recordId))
                }
                else -> {
                    call.respondRedirect(this@redCapModule.followupRedCapAPI.exportSurveyQueueLink(participant.followupSurveyRecordId))
                }
            }
        }
        route("/hcp/{id}/{secret}") {
            get("/") {
                call.checkHcpLink { _, _ ->
                    respondConsentTemplate(informationSheet = INFO_SHEET_HCP_STUDY_HCPS)
                }
            }
            post("/") {
                call.checkHcpLink { hcpLink, participant ->
                    if (receiveParameters().contains("consent")) {
                        if (hcpLink.surveyRecordId == null) {
                            val enrollmentType = when (participant.enrollmentType) {
                                EnrollmentType.ADULT_USING_DIYAPS -> 0
                                EnrollmentType.ADULT_NOT_USING_DIYAPS -> 1
                                else -> throw IllegalStateException("HCP study not enabled for non-adults")
                            }
                            val recordId = hcpRedCapAPI.createRecord(hcpLink.participantId, enrollmentType)
                            this@redCapModule.database.setSurveyRecordIdForHcpLink(hcpLink.id, recordId)
                            call.respondRedirect(hcpRedCapAPI.exportSurveyQueueLink(recordId))
                        } else {
                            call.respondRedirect(hcpRedCapAPI.exportSurveyQueueLink(hcpLink.surveyRecordId))
                        }
                    } else {
                        call.respondConsentNotGivenTemplate("/hcp/${hcpLink.id}/${hcpLink.secret}")
                    }
                }
            }
        }
        /*route("/p/{id}/{secret}") {
            get("/") {
                call.checkParticipationLink { participationLink ->
                    when (participationLink.enrollmentType) {
                        EnrollmentType.PARTNER_USING_DIYAPS -> respondConsentTemplate(informationSheet = INFO_SHEET_ADULT_USERS_PARTNERS)
                        EnrollmentType.PARTNER_NOT_USING_DIYAPS -> respondConsentTemplate(informationSheet = INFO_SHEET_ADULT_NON_USERS_PARTNERS)
                        EnrollmentType.TEENAGER_USING_DIYAPS -> respondConsentTemplate(informationSheet = INFO_SHEET_TEENAGERS)
                        else -> throw IllegalStateException("EnrollmentType not supported")
                    }
                }
            }
            post("/") {
                call.checkParticipationLink() { participationLink ->
                    if (receiveParameters().contains("consent")) {
                        if (participationLink.surveyRecordId == null) {
                            val recordId = redCapAPI.createRecord(participationLink.participantId, participationLink.id, participationLink.enrollmentType)
                            this@redCapModule.database.setSurveyRecordIdForParticipationLink(participationLink.id, recordId)
                            call.respondRedirect(redCapAPI.exportSurveyQueueLink(recordId))
                        } else {
                            call.respondRedirect(redCapAPI.exportSurveyQueueLink(participationLink.surveyRecordId))
                        }
                    } else {
                        call.respondConsentNotGivenTemplate("/p/${participationLink.id}/${participationLink.secret}")
                    }
                }
            }
        }*/
    }
}

private suspend fun ApplicationCall.checkHcpLink(block: suspend ApplicationCall.(HcpLink, Participant) -> Unit) {
    val id = parameters["id"]?.toLongOrNull()
    val secret = parameters["secret"]
    if (id != null && secret != null) {
        val participationLink = application.database.getHcpLink(id)
        if (participationLink?.secret == secret) {
            val participant = application.database.getParticipant(participationLink.participantId)!!
            block(participationLink, participant)
        } else {
            respond(HttpStatusCode.NotFound)
        }
    }
}
