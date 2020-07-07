package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondBranchingTemplate(
    title: String,
    subtitle: String?,
    text: String,
    backUrl: String? = null,
    block: MAIN.() -> Unit
) =
    respondBaseTemplate(title, subtitle) {
        div {
            id = "branching-text"
            text(text)
        }
        block()
        if (backUrl != null) {
            a(backUrl) {
                id = "go-back"
                text(language.goBack)
            }
        }
    }

fun HtmlBlockTag.branchingOption(icon: String?, text: String, href: String) {
    a(href, classes = "branching-option") {
        if (icon != null) {
            img(alt = "", src = icon)
        }
        span {
            unsafe {
                raw(text)
            }
        }
        img(alt = "", src = "/static/arrow-right-gray.svg")
    }
}
