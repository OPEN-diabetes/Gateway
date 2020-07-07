package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.p

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
            text(cardText)
        }
    }
    if (backUrl != null) {
        a(backUrl) {
            id = "go-back"
            text(language.goBack)
        }
    }
}

suspend fun ApplicationCall.respondChildHasAlreadyParticipatedTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.childHasAlreadyParticipated,
    cardText = language.askChildForParticipationLink,
    backUrl = "/new_participant/parent/using_diyaps"
)

suspend fun ApplicationCall.respondParentHasAlreadyParticipatedTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.parentHasAlreadyParticipated,
    cardText = language.askParentForParticipationLink,
    backUrl = "/new_participant/teenager/using_diyaps"
)

suspend fun ApplicationCall.respondChildNotUsingDIYAPSTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.childNotUsingDIYAPS,
    cardText = language.notWithinAudience,
    backUrl = "/new_participant/parent/"
)

suspend fun ApplicationCall.respondTeenagerNotUsingDIYAPSTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.notUsingDIYAPS,
    cardText = language.notWithinAudience,
    backUrl = "/new_participant/teenager/"
)

suspend fun ApplicationCall.respondAdultNotUsingDIYAPSTemplate() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.notUsingDIYAPS,
    cardText = language.notWithinAudience,
    backUrl = "/new_participant/adult/"
)

suspend fun ApplicationCall.respondAskPatientForLink() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.youAreAHCP,
    cardText = language.askPatientForParticipationLink,
    backUrl = "/new_participant"
)

suspend fun ApplicationCall.respondAskPartnerForLink() = respondErrorTemplate(
    title = language.newParticipant,
    subtitle = null,
    cardTitle = language.yourPartnerHasDiabetes,
    cardText = language.askPartnerForParticipationLink,
    backUrl = "/new_participant"
)
