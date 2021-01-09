package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.database.HcpLink
import eu.opendiabetes.gateway.database.Participant
import eu.opendiabetes.gateway.database.ParticipationLink
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_HCPS
import eu.opendiabetes.gateway.templates.respondConsentTemplate
import eu.opendiabetes.gateway.utils.RedCapAPI
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.*


fun Application.redCapModule() {
    val redCapAPI = RedCapAPI(
        getStringProperty("gateway.redcap.apiUrl"),
        getStringProperty("gateway.redcap.token")
    )
    routing {
        get("/survey") {
            val participant = call.participant
            when {
                participant == null -> call.respondRedirect("/")
                participant.surveyRecordId != null -> call.respondRedirect("/")
                participant.followupSurveyRecordId == null && participant.enrollmentType.followupId != null -> {
                    val recordId = redCapAPI.createRecord(participant.id, null, participant.enrollmentType.followupId)
                    this@redCapModule.database.setFollowupSurveyRecordIdForParticipant(participant.id, recordId)
                    call.respondRedirect(redCapAPI.exportSurveyQueueLink(recordId))
                }
                participant.followupSurveyRecordId != null -> call.respondRedirect(redCapAPI.exportSurveyQueueLink(participant.followupSurveyRecordId))
                else -> call.respondRedirect("/")
            }
        }
        route("/hcp/{id}/{secret}") {
            get ("/") {
                call.checkHcpLink { _, _ ->
                    respondConsentTemplate(informationSheet = INFO_SHEET_HCPS)
                }
            }
            post("/") {
                call.checkHcpLink { participant, hcpLink ->
                    if (hcpLink.surveyRecordId == null) {
                        val enrollmentType = when(participant.enrollmentType) {
                            EnrollmentType.ADULT_USING_DIYAPS -> 4
                            EnrollmentType.ADULT_NOT_USING_DIYAPS -> 5
                            else -> throw IllegalStateException("No HCP survey for enrollment type")
                        }
                        val recordId = redCapAPI.createRecord(participant.id, null, enrollmentType)
                        this@redCapModule.database.setSurveyRecordIdForHcpLink(hcpLink.id, recordId)
                        call.respondRedirect(redCapAPI.exportSurveyQueueLink(recordId))
                    } else {
                        call.respondRedirect(redCapAPI.exportSurveyQueueLink(hcpLink.surveyRecordId))
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

private suspend fun ApplicationCall.checkHcpLink(block: suspend ApplicationCall.(Participant, HcpLink) -> Unit) {
    val id = parameters["id"]?.toLongOrNull()
    val secret = parameters["secret"]
    if (id != null && secret != null) {
        val participant = application.database.getParticipant(id)
        val hcpLink = application.database.getHcpLinkForParticipant(id)
        if (participant != null && hcpLink?.secret == secret) {
            block(participant, hcpLink)
        } else {
            respond(HttpStatusCode.Unauthorized)
        }
    }
}

private suspend fun ApplicationCall.checkParticipationLink(block: suspend ApplicationCall.(ParticipationLink) -> Unit) {
    val id = parameters["id"]?.toLongOrNull()
    val secret = parameters["secret"]
    if (id != null && secret != null) {
        val participationLink = application.database.getParticipationLink(id)
        if (participationLink?.secret == secret) {
            block(participationLink)
        } else {
            respond(HttpStatusCode.Unauthorized)
        }
    }
}
