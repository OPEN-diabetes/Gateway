package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.templates.respondGDPRTemplate
import io.ktor.application.*
import io.ktor.routing.*

fun Application.gdprModule() {
    routing {
        get("gdpr") {
            call.respondGDPRTemplate()
        }
    }
}
