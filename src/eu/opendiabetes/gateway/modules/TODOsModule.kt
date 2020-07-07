package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.database.ParticipationLink
import eu.opendiabetes.gateway.templates.ParticipationLinkTODO
import eu.opendiabetes.gateway.templates.respondTODOsTemplate
import eu.opendiabetes.gateway.utils.database
import eu.opendiabetes.gateway.utils.formatParticipantId
import eu.opendiabetes.gateway.utils.getSurveyLinksForParticipant
import eu.opendiabetes.gateway.utils.participant
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.features.origin
import io.ktor.request.host
import io.ktor.request.port
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.todosModule() {
    routing {
        get("/todos") {
            val participant = call.participant
            if (participant != null) {
                val participationLinks = this@todosModule.database.getSurveyLinksForParticipant(participant.id).map {
                    when (it.enrollmentType) {
                        EnrollmentType.PARENT_USING_DIYAPS -> ParticipationLinkTODO.Parent(call.constructParticipationURL(it))
                        EnrollmentType.TEENAGER_USING_DIYAPS -> ParticipationLinkTODO.Child(call.constructParticipationURL(it))
                        EnrollmentType.PARTNER_USING_DIYAPS -> ParticipationLinkTODO.Partner(call.constructParticipationURL(it))
                        EnrollmentType.PARTNER_NOT_USING_DIYAPS -> ParticipationLinkTODO.Partner(call.constructParticipationURL(it))
                        EnrollmentType.HCP_USING_DIYAPS -> ParticipationLinkTODO.HCP(call.constructParticipationURL(it))
                        else -> throw IllegalArgumentException("Invalid enrollmentType")
                    }
                }
                call.respondTODOsTemplate(formatParticipantId(participant.id, participant.secret), participationLinks)
            } else {
                call.respondRedirect("login")
            }
        }
    }
}

private fun ApplicationCall.constructParticipationURL(participationLink: ParticipationLink): String {
    val scheme = request.origin.scheme
    val port = when {
        scheme == "https" && request.port() == 443 -> ""
        scheme == "http" && request.port() == 80 -> ""
        else -> ":" + request.port().toString()
    }
    return "$scheme://${request.host()}$port/p/${participationLink.participantId}/${participationLink.secret}"
}
