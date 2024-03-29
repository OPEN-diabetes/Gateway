package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.OpenHumansAPI
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.*
import io.ktor.http.*
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
                    } else {
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
            if (showHCPSurvey) {
                if (hcpUrl == null) {
                    li {
                        id ="new-sign"
                        img(src = "/static/new.svg")
                    }
                }
                li {
                    text(language.inviteYourHcp)
                    if (hcpUrl == null) {
                        p {
                            unsafe {
                                raw(language.hcpInfoText)
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
                a(
                    "/openhumans",
                    target = "_blank",
                    classes = "button"
                ) { text(if (dataSources == null) language.setup else language.setupAgain) }
            }
        }
        a("/logout") {
            id = "go-back"
            text(language.signOut)
        }
    }
