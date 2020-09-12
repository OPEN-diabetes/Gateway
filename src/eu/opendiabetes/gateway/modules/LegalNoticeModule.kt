package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.templates.respondGDPRTemplate
import eu.opendiabetes.gateway.templates.respondLegalNoticeTemplate
import io.ktor.application.*
import io.ktor.routing.*

fun Application.legalNoticeModule() {
    routing {
        get("legal-notice") {
            call.respondLegalNoticeTemplate()
        }
    }
}
