package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.DEFAULT_LANGUAGE
import eu.opendiabetes.gateway.GatewaySession
import eu.opendiabetes.gateway.LANGUAGES
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.sessions.*

fun Application.sessionsModule() {
    install(Sessions) {
        cookie<GatewaySession>("SESSION") {
            cookie.extensions["SameSite"] = "lax"
            cookie.maxAgeInSeconds = Long.MAX_VALUE
        }
    }
    intercept(ApplicationCallPipeline.Features) {
        val browserSession = call.sessions.get<GatewaySession>() ?: GatewaySession()
        val requestedLanguage = call.request.queryParameters["lang"]
        call.language = if (requestedLanguage != null) {
            val resolved = LANGUAGES.firstOrNull { it.code == requestedLanguage }
            if (resolved != null) {
                call.sessions.set(browserSession.copy(languageCode = requestedLanguage))
                resolved
            } else {
                LANGUAGES.firstOrNull { it.code == browserSession.languageCode } ?: DEFAULT_LANGUAGE
            }
        } else {
            LANGUAGES.firstOrNull { it.code == browserSession.languageCode } ?: DEFAULT_LANGUAGE
        }
        if (browserSession.sessionId != null && browserSession.sessionSecret != null) {
            val session = database.getSession(browserSession.sessionId, browserSession.sessionSecret)
            if (session != null) {
                call.session = session
                call.participant = database.getParticipant(session.id)!!
            }
        }
    }
}
