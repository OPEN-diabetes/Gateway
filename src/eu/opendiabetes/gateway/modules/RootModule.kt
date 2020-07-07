package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.utils.session
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.rootModule() {
    routing {
        get("/") {
            if (call.session == null) {
                call.respondRedirect("/login")
            } else {
                call.respondRedirect("/todos")
            }
        }
    }
}
