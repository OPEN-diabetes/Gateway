package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.language.info_sheets.gdpr.GDPR_ENGLISH
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondGDPRTemplate() = respondBaseTemplate(
    language.gdpr,
    null
) {
    div {
        id = "gdpr"
        unsafe {
            raw(GDPR_ENGLISH)
        }
    }
}
