package eu.opendiabetes.gateway.utils

import eu.opendiabetes.gateway.GatewayAttributes
import io.ktor.util.pipeline.Pipeline

var Pipeline<*, *>.database
    get() = attributes[GatewayAttributes.database]
    set(value) {
        attributes.put(GatewayAttributes.database, value)
    }
