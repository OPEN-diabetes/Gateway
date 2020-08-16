package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.templates.respondBaseTemplate
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondLoginTemplate(participantId: String? = null) = respondBaseTemplate(
    title = language.login,
    subtitle = null
) {
    div {
        id = "login"
        div {
            id = "login-text"
            text(language.loginText)
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
        span {
            id = "openhumans-login-hint"
            text(language.ohLoginNotice)
        }
        a("/") {
            id = "go-back"
            text(language.goBack)
        }
    }
}
