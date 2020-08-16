package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.templates.respondLandingPageTemplate
import eu.opendiabetes.gateway.utils.session
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.landingPageModule() {
    routing {
        get("/") {
            if (call.session == null) {
                call.respondLandingPageTemplate()
            } else {
                call.respondRedirect("/todos")
            }
        }
    }
}
