package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.language.Language
import eu.opendiabetes.gateway.utils.OpenHumansAPI
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import io.ktor.http.formUrlEncode
import kotlinx.html.*

suspend fun ApplicationCall.respondTODOsTemplate(
    participantId: String,
    dataSources: Set<String>?,
    showFollowupSurvey: Boolean,
    hasFilledOutFollowupSurvey: Boolean,
    showHCPSurvey: Boolean,
    hcpUrl: String?
) =
    respondBaseTemplate(
        language.yourTODOs,
        language.participantId(participantId)
    ) {
        div {
            id = "todos-header"
            text(language.weKindlyAskYou)
        }
        ul {
            id = "todos"
            li {
                if (showFollowupSurvey) {
                    if (hasFilledOutFollowupSurvey) {
                        text(language.youHaveFilledOutTheSurvey)
                        p {
                            unsafe {
                                raw(language.thanksForFillingOutSurvey)
                            }
                        }
                    } else{
                        text(language.fillOutSurvey)
                        p {
                            unsafe {
                                raw(language.answerAFewQuestions)
                            }
                        }
                        a("/survey", "_blank", "button") { text(language.goToSurvey) }
                    }
                } else {
                    text(language.surveyClosed)
                    p {
                        text(language.thanksToParticipants)
                    }
                }
            }
            li {
                text(language.linkToOpenHumans)
                when {
                    dataSources == null -> p {
                        unsafe {
                            raw(language.linkToOpenHumansTextSetup)
                        }
                    }
                    dataSources.isEmpty() -> p("warning") {
                        unsafe {
                            raw(language.linkToOpenHumansTextDataSources)
                        }
                    }
                    else -> {
                        p {
                            unsafe {
                                raw(language.linkToOpenHumansTextDataSources)
                            }
                        }
                        ul {
                            if (dataSources.contains(OpenHumansAPI.NIGHTSCOUT_ID)) li { span("success") { text(language.nightscoutDataTransfer) } }
                            if (dataSources.contains(OpenHumansAPI.ANDROIDAPS_ID)) li { span("success") { text(language.androidAPSUploader) } }
                            if (dataSources.contains(OpenHumansAPI.SELFIES_ID)) li { span("success") { text(language.dataSelfie) } }
                        }
                    }
                }
                a("/openhumans", target = "_blank", classes =  "button") { text(if (dataSources == null) language.setup else language.setupAgain) }
            }
            if (showHCPSurvey) {
                li {
                    text(language.inviteYourHcp)
                    if (hcpUrl == null) {
                        p {
                            unsafe {
                                raw(language.clickForMoreInformation)
                            }
                        }
                        a("/hcp-study", classes = "button") { text("Learn more") }
                    } else {
                        p {
                            unsafe {
                                raw(language.fillOutForm)
                            }
                        }
                        input(type = InputType.url, classes = "participation-link") {
                            readonly = true
                            value = hcpUrl
                        }
                        val mailtoLink = "mailto:?" + listOf(
                            "subject" to language.invitationToSurvey,
                            "body" to language.invitationText(hcpUrl)
                        ).formUrlEncode().replace("+", "%20")
                        a(mailtoLink, "_blank", "email-link") {
                            text(language.shareViaEmail)
                        }
                    }
                }
            }
        }
        a("/logout") {
            id = "go-back"
            text(language.signOut)
        }
    }

private fun LI.participationLink(language: Language, title: String, desc: String, link: String) {
    text(title)
    p {
        unsafe {
            raw(desc)
        }
    }
    input(type = InputType.url, classes = "participation-link") {
        readonly = true
        value = link
    }
    val mailtoLink = "mailto:?" + listOf(
        "subject" to language.invitationToSurvey,
        "body" to language.invitationText(link)
    ).formUrlEncode().replace("+", "%20")
    a(mailtoLink, "_blank", "email-link") {
        text("Share via e-mail")
    }
}

sealed class ParticipationLinkTODO(val link: String) {
    class Parent(link: String) : ParticipationLinkTODO(link)
    class Child(link: String) : ParticipationLinkTODO(link)
    class Partner(link: String) : ParticipationLinkTODO(link)
}
