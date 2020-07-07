package eu.opendiabetes.gateway.utils

import eu.opendiabetes.gateway.GatewayAttributes
import io.ktor.application.ApplicationCall

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
