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
                    placeholder = "123-A1B2C3D4F6"
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
    }
}
