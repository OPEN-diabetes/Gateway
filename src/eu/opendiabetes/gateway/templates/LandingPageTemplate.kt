package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.unsafe

suspend fun ApplicationCall.respondLandingPageTemplate() =
    respondBaseTemplate(language.hiThere, language.thanksForHelpingUs) {
        img(src = "/static/open.svg") {
            id = "open-logo"
        }
        div {
            id = "introduction"
            unsafe {
                raw(language.introduction)
            }
        }
        div {
            id = "branching-text"
            text(language.doYouAlreadyHaveAParticipantId)
        }
        branchingOption("/static/register.svg", language.iDoNotHaveAParticipantId, "/new_participant")
        branchingOption("/static/login.svg", language.iAlreadyHaveAParticipantId, "/login")
    }
