package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.ParticipationLink
import eu.opendiabetes.gateway.templates.respondConsentNotGivenTemplate
import eu.opendiabetes.gateway.templates.respondConsentTemplate
import eu.opendiabetes.gateway.utils.RedCapAPI
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing


fun Application.redCapModule() {
    val redCapAPI = RedCapAPI(
        getStringProperty("gateway.redcap.apiUrl"),
        getStringProperty("gateway.redcap.token")
    )
    routing {
        get("/survey") {
            val participant = call.participant
            if (participant != null) {
                if (participant.surveyRecordId == null) {
                    val recordId = redCapAPI.createRecord(participant.id, participant.enrollmentType)
                    this@redCapModule.database.setSurveyRecordIdForParticipant(participant.id, recordId)
                    call.respondRedirect(redCapAPI.exportSurveyQueueLink(recordId))
                } else {
                    call.respondRedirect(redCapAPI.exportSurveyQueueLink(participant.surveyRecordId))
                }
            } else {
                call.respondRedirect("/")
            }
        }
        route("/p/{id}/{secret}") {
            get("/") {
                call.checkParticipationLink() {
                    respondConsentTemplate()
                }
            }
            post("/") {
                call.checkParticipationLink() { participationLink ->
                    if (receiveParameters().contains("consent")) {
                        if (participationLink.surveyRecordId == null) {
                            val recordId = redCapAPI.createRecord(participationLink.id, participationLink.enrollmentType)
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
