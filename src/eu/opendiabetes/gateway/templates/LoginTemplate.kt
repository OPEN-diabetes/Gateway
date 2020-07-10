package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.templates.respondBaseTemplate
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondLoginTemplate(participantId: String? = null) = respondBaseTemplate(
    title = language.hiThere,
    subtitle = language.thanksForHelpingUs
) {
    div {
        id = "login"
        div {
            id = "login-text"
            unsafe {
                raw(language.loginText("new_participant"))
            }
        }
        form("/login", method = FormMethod.post) {
            id = "login-form"
            div {
                id = "id-input-container"
                div {
                    id = "id-symbol"
                    text("#")
                }
                input(InputType.text, name = "participantid") {
                    id = "id-input"
                    if (participantId != null) {
                        value = participantId
                    }
                    placeholder = "123-A1B-2C3-D4F"
                }
            }
            button(type = ButtonType.submit) {
                id = "submit-button"
                img(language.submit, "/static/arrow-right-white.svg")
            }
        }
        if (participantId != null) {
            div {
                id = "login-error"
                text(language.unknownId)
            }
        }
        a("/openhumans/login") {
            id = "openhumans-login-button"
            text(language.orSignInUsingOpenHumans)
        }
    }
}
