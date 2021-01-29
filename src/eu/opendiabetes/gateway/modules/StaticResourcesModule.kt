package eu.opendiabetes.gateway.modules

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.*
import io.ktor.routing.*

fun Application.staticResourcesModule() {
    routing {
        route("static") {
            intercept(ApplicationCallPipeline.Features) {
                call.response.headers.append(HttpHeaders.CacheControl, "Max-Age=3600")
                proceed()
            }
            static { resources("static") }
        }
    }
}
