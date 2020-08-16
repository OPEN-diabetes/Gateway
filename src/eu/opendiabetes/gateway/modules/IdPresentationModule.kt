package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.templates.respondIdPresentationTemplate
import eu.opendiabetes.gateway.utils.formatParticipantId
import eu.opendiabetes.gateway.utils.participant
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.idPresentationModule() {
    routing {
        get("present_id") {
            val participant = call.participant
            if (participant == null) {
                call.respondRedirect("/")
            } else {
                call.respondIdPresentationTemplate(formatParticipantId(participant.id, participant.secret))
            }
        }
    }
}
