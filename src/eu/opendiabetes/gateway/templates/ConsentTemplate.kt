package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.info_sheets.english.INFO_SHEET_ADULT_USERS
import eu.opendiabetes.gateway.info_sheets.english.INFO_SHEET_ADULT_USERS_PARTNERS
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondConsentTemplate(
    backUrl: String? = null,
    informationSheet: String
) = respondBaseTemplate(
    language.declarationOfConsent,
    null
) {
    div {
        id = "consent-form"
        div {
            id = "consortium-logos"
            arrayOf("open.svg", "ucd.png", "charite.svg", "uoc.svg", "dedoc.png", "sdcc.png", "acbrd.png").forEach {
                img(src = "/static/$it")
            }
        }
        div {
            id = "consent-content"
            unsafe {
                raw(informationSheet)
            }
        }
        form(method = FormMethod.post) {
            input(InputType.checkBox, name = "consent") {
                id = "consent"
            }
            label {
                htmlFor = "consent"
                text(language.iUnderstandAndAgree)
            }
            input(InputType.submit)
        }
    }
    if (backUrl != null) {
        a(backUrl) {
            id = "go-back"
            text(language.goBack)
        }
    }
}
