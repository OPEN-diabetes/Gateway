package eu.opendiabetes.gateway.modules

import io.ktor.application.Application
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.routing

fun Application.staticResourcesModule() {
    routing {
        static("static") {
            resources("static")
        }
    }
}
