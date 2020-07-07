package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.templates.respondBaseTemplate
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.id

suspend fun ApplicationCall.respondIdPresentationTemplate(participantId: String) =
    respondBaseTemplate(language.newParticipant, null) {
        div {
            id = "id-presentation"
            div("instruction") {
                text(language.yourParticipantIdIs)
            }
            div {
                id = "participant-id"
                text(participantId)
            }
            div("instruction") {
                text(language.pleaseNoteItDownToAvoidLosingIt)
            }
            a("todos", classes = "button") {
                id = "proceed"
                text(language.proceed)
            }
        }
    }
