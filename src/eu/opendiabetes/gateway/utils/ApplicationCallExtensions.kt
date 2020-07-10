package eu.opendiabetes.gateway.utils

import eu.opendiabetes.gateway.GatewayAttributes
import io.ktor.application.ApplicationCall
import io.ktor.features.origin
import io.ktor.http.URLBuilder
import io.ktor.request.host
import io.ktor.request.port

var ApplicationCall.language
    get() = attributes[GatewayAttributes.language]
    set(value) {
        attributes.put(GatewayAttributes.language, value)
    }

var ApplicationCall.session
    get() = attributes.getOrNull(GatewayAttributes.session)
    set(value) {
        attributes.put(GatewayAttributes.session, value!!)
    }

var ApplicationCall.participant
    get() = attributes.getOrNull(GatewayAttributes.participant)
    set(value) {
        attributes.put(GatewayAttributes.participant, value!!)
    }

fun ApplicationCall.constructURL(path: String): String {
    val scheme = request.origin.scheme
    val port = when {
        scheme == "https" && request.port() == 443 -> ""
        scheme == "http" && request.port() == 80 -> ""
        else -> ":" + request.port().toString()
    }
    return "$scheme://${request.host()}$port$path"
}
