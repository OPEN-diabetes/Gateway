package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.language.info_sheets.gdpr.GDPR_ENGLISH
import eu.opendiabetes.gateway.language.info_sheets.legalNotice.LEGAL_NOTICE_ENGLISH
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondLegalNoticeTemplate() = respondBaseTemplate(
    language.gdpr,
    null
) {
    div {
        id = "legal-notice"
        unsafe {
            raw(LEGAL_NOTICE_ENGLISH)
        }
    }
}
