package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondLandingPageTemplate() =
    respondBaseTemplate(language.hiThere, language.thanksForHelpingUs) {
        img(src = "/static/open.svg") {
            id = "open-logo"
        }
        div("introduction") {
            unsafe {
                raw(language.introduction1)
            }
        }
        div {
            id = "branching-text"
            text(language.doYouAlreadyHaveAParticipantId)
        }
        //branchingOption("/static/register.svg", language.iDoNotHaveAParticipantId, "/new_participant")
        branchingOption("/static/login.svg", language.iAlreadyHaveAParticipantId, "/login")
        div("introduction") {
            unsafe {
                raw(language.introduction2)
            }
        }
        img(src = "/static/team.jpg") {
            id = "team"
        }
        div {
            id = "funding-notice"
            img(src = "/static/europe.svg") {
                id = "eu-logo"
            }
            span {
                text(language.fundingNotice)
            }
        }
    }
