package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import kotlinx.html.*

suspend fun ApplicationCall.respondBaseTemplate(
    title: String,
    subtitle: String?,
    content: MAIN.() -> Unit
) = respondHtml {
    lang = language.code
    head {
        meta("charset", "UTF-8")
        meta("viewport", "width=device-width, initial-scale=1.0")
        link("/static/favicon.svg", "icon", "image/svg+xml")
        styleLink("/static/style.css")
        title(title)
    }
    body {
        header {
            id = "header"
            /*ul("link-list") {
                id = "languages"
                LANGUAGES.forEach {
                    li(if (it == language) "selected" else null) {
                        lang = it.code
                        val langUrl = Parameters.build {
                            appendAll(request.queryParameters)
                            set("lang", it.code)
                        }.formUrlEncode()
                        a("?$langUrl") {
                            text(it.name)
                        }
                    }
                }
            }*/
            span {
                id = "title"
                text(title)
            }
            if (subtitle != null) {
                span {
                    id = "subtitle"
                    unsafe {
                        raw(subtitle)
                    }
                }
            }
        }
        div("spacer")
        main {
            id = "main"
            content()
        }
        div("spacer")
        footer {
            id = "footer"
            language.genderNotice?.let { genderNotice ->
                div {
                    id = "gender-notice"
                    text(genderNotice)
                }
            }
            ul("link-list") {
                id = "footer-menu"
                li {
                    a("/gdpr") {
                        text(language.gdpr)
                    }
                }
                li {
                    a("/legal-notice") {
                        text(language.legalNotice)
                    }
                }
            }
            img("", "/static/branding.svg") {
                id = "branding"
            }
        }
    }
}
