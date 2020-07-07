package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.GatewaySession
import eu.opendiabetes.gateway.templates.respondLoginTemplate
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set

fun Application.loginModule() {
    routing {
        route("login") {
            intercept(ApplicationCallPipeline.Call) {
                if (call.session != null) {
                    call.respondRedirect("/todos")
                    finish()
                } else {
                    proceed()
                }
            }
            get("/") {
                call.respondLoginTemplate()
            }
            post("/") {
                val participantId = call.receiveParameters()["participantid"]
                val split = participantId?.split(Regex("-"), 2)
                if (split == null || split.size != 2) {
                    call.respondLoginTemplate(participantId ?: "")
                } else {
                    val id = split[0].toLongOrNull()
                    if (id == null) {
                        call.respondLoginTemplate(participantId)
                    } else {
                        val participant = this@loginModule.database.getParticipant(id)
                        if (participant != null && participant.secret == split[1].replace("-", "").toUpperCase()) {
                            val (sessionId, _, sessionSecret) = this@loginModule.database.createSession(id)
                            val currentSession = call.sessions.get<GatewaySession>() ?: GatewaySession()
                            call.sessions.set(currentSession.copy(sessionId = sessionId, sessionSecret = sessionSecret))
                            call.respondRedirect("/todos")
                        } else {
                            call.respondLoginTemplate(formatParticipantId(id, split[1]))
                        }
                    }
                }
            }
        }
        get("logout") {
            val session = call.session
            if (session != null) {
                this@loginModule.database.deleteSession(session.id)
            }
            call.sessions.set(
                (call.sessions.get<GatewaySession>() ?: GatewaySession()).copy(
                    sessionId = null,
                    sessionSecret = null
                )
            )
            call.respondRedirect("/login")
        }
    }
}
