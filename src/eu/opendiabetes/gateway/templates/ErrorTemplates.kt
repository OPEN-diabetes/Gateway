package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondErrorTemplate(
    title: String,
    subtitle: String?,
    cardTitle: String,
    cardText: String,
    backUrl: String? = null
) = respondBaseTemplate(title, subtitle) {
    div {
        id = "card"
        p {
            id = "card-title"
            text(cardTitle)
        }
        p {
            id = "card-error"
            unsafe {
                raw(cardText)
            }
        }
    }
    if (backUrl != null) {
        a(backUrl) {
            id = "go-back"
            text(language.goBack)
        }
    }
}

suspend fun ApplicationCall.respondConsentNotGivenTemplate(backUrl: String) = respondErrorTemplate(
    title = language.declarationOfConsent,
    subtitle = null,
    cardTitle = language.consentNotGiven,
    cardText = language.consentNotGivenDescription,
    backUrl = backUrl
)

suspend fun ApplicationCall.respondNoParticipantFoundTemplate() = respondErrorTemplate(
    title = language.openHumans,
    subtitle = null,
    cardTitle = language.noParticipantIdFound,
    cardText = language.createNewParticipantID("/new_participant"),
    backUrl = "/login"
)

suspend fun ApplicationCall.respondAuthorizeErrorTemplate() = respondErrorTemplate(
    title = language.openHumans,
    subtitle = null,
    cardTitle = language.somethingWentWrong,
    cardText = language.couldntConnectToOpenHumans,
    backUrl = "/openhumans"
)

suspend fun ApplicationCall.respondWrongAccountTemplate() = respondErrorTemplate(
    title = language.openHumans,
    subtitle = null,
    cardTitle = language.wrongAccount,
    cardText = language.pleaseReuseAccount,
    backUrl = "/openhumans"
)

suspend fun ApplicationCall.respondAccountAlreadyLinkedTemplate() = respondErrorTemplate(
    title = language.openHumans,
    subtitle = null,
    cardTitle = language.accountAlreadyLinked,
    cardText = language.accountAlreadyLinkedDescription,
    backUrl = "/openhumans"
)

suspend fun ApplicationCall.respondAskParentForLinkTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.youAreATeenagerWithDiabetesAndWantToParticipate,
    cardText = language.askParentForParticipationLink,
    backUrl = "/new_participant"
)

suspend fun ApplicationCall.respondAskPartnerForLinkTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.yourPartnerHasDiabetes,
    cardText = language.askPartnerForParticipationLink,
    backUrl = "/new_participant"
)
