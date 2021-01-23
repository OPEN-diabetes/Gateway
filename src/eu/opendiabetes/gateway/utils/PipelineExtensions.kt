package eu.opendiabetes.gateway.utils

import eu.opendiabetes.gateway.GatewayAttributes
import io.ktor.util.pipeline.Pipeline

var Pipeline<*, *>.database
    get() = attributes[GatewayAttributes.database]
    set(value) {
        attributes.put(GatewayAttributes.database, value)
    }

var Pipeline<*, *>.openHumansApi
    get() = attributes[GatewayAttributes.openHumansAPI]
    set(value) {
        attributes.put(GatewayAttributes.openHumansAPI, value)
    }

var Pipeline<*, *>.followupRedCapAPI
    get() = attributes[GatewayAttributes.followupRedCapAPI]
    set(value) {
        attributes.put(GatewayAttributes.followupRedCapAPI, value)
    }
