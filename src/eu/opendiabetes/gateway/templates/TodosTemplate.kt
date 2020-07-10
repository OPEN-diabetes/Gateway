package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.OpenHumansAPI
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondTODOsTemplate(
    participantId: String,
    participationLinks: List<ParticipationLinkTODO>,
    dataSources: Set<String>?
) =
    respondBaseTemplate(
        language.yourTODOs,
        language.participantId(participantId)
    ) {
        ul {
            id = "todos"
            li {
                text(language.fillOutSurvey)
                p { text(language.answerAFewQuestions) }
                a("/survey", "_blank", "button") { text(language.goToSurvey) }
            }
            li {
                text(language.linkToOpenHumans)
                when {
                    dataSources == null -> p { text(language.linkToOpenHumansTextSetup) }
                    dataSources.isEmpty() -> p("warning") { text(language.linkToOpenHumansTextNoData) }
                    else -> {
                        p { text(language.linkToOpenHumansTextDataSources) }
                        ul {
                            if (dataSources.contains(OpenHumansAPI.NIGHTSCOUT_ID)) li { span("success") { text(language.nightscoutDataTransfer) } }
                            if (dataSources.contains(OpenHumansAPI.ANDROIDAPS_ID)) li { span("success") { text(language.androidAPSUploader) } }
                            if (dataSources.contains(OpenHumansAPI.SELFIES_ID)) li { span("success") { text(language.dataSelfie) } }
                        }
                    }
                }
                a("/openhumans", classes =  "button") { text(if (dataSources == null) language.setup else language.setupAgain) }
            }
            participationLinks.forEach {
                li {
                    when (it) {
                        is ParticipationLinkTODO.Parent -> {
                            participationLink(language.askParent, language.sendParent, it.link)
                        }
                        is ParticipationLinkTODO.Child -> {
                            participationLink(language.askChild, language.sendChild, it.link)
                        }
                        is ParticipationLinkTODO.Partner -> {
                            participationLink(language.askPartner, language.sendPartner, it.link)
                        }
                        is ParticipationLinkTODO.HCP -> {
                            participationLink(language.askHCP, language.sendHCP, it.link)
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

private fun LI.participationLink(title: String, desc: String, link: String) {
    text(title)
    p { text(desc) }
    input(type = InputType.url, classes = "participation-link") {
        readonly = true
        onClick = "this.select();"
        value = link
    }
}

sealed class ParticipationLinkTODO(val link: String) {
    class Parent(link: String) : ParticipationLinkTODO(link)
    class Child(link: String) : ParticipationLinkTODO(link)
    class HCP(link: String) : ParticipationLinkTODO(link)
    class Partner(link: String) : ParticipationLinkTODO(link)
}
