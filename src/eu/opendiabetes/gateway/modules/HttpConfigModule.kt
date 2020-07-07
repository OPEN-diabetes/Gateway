package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.utils.getBooleanProperty
import eu.opendiabetes.gateway.utils.getLongProperty
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*

fun Application.httpConfigModule() {
    install(AutoHeadResponse)
    install(DefaultHeaders)

    if (getBooleanProperty("gateway.reverseProxy.enableForwardedHeader")) {
        install(ForwardedHeaderSupport)
    }

    if (getBooleanProperty("gateway.reverseProxy.enableXForwardedHeader")) {
        install(XForwardedHeaderSupport)
    }

    if (getBooleanProperty("gateway.hsts.enable")) {
        install(HSTS) {
            preload = getBooleanProperty("gateway.hsts.preload")
            includeSubDomains = getBooleanProperty("gateway.hsts.includeSubDomains")
            maxAgeInSeconds = getLongProperty("gateway.hsts.maxAgeInSeconds")
        }
    }

    if (getBooleanProperty("gateway.httpsRedirect.enable")) {
        install(HttpsRedirect) {
            permanentRedirect = false
        }
    }
}
