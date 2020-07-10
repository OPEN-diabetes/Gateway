package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.utils.RedCapAPI
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing


fun Application.redCapModule() {
    val redCapAPI = RedCapAPI(
        getStringProperty("gateway.redcap.apiUrl"),
        getStringProperty("gateway.redcap.token")
    )
    routing {
        get("survey") {
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
                call.respondRedirect("login")
            }
        }
        route("p/{participantId}/{secret}") {
            get("/") {
                val id = call.parameters["participantId"]?.toLongOrNull()
                val secret = call.parameters["secret"]
                if (id != null && secret != null) {
                    val participationLink = this@redCapModule.database.getParticipationLink(id)
                    if (participationLink?.secret == secret) {
                        if (participationLink.surveyRecordId == null) {
                            val recordId = redCapAPI.createRecord(id, participationLink.enrollmentType)
                            this@redCapModule.database.setSurveyRecordIdForParticipationLink(id, recordId)
                            call.respondRedirect(redCapAPI.exportSurveyQueueLink(recordId))
                        } else {
                            call.respondRedirect(redCapAPI.exportSurveyQueueLink(participationLink.surveyRecordId))
                        }
                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                }
            }
        }
    }
}
