package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.GatewaySession
import eu.opendiabetes.gateway.templates.*
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.http.HttpMethod
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import java.util.concurrent.TimeUnit

private const val OPEN_HUMANS_AUTH_PROVIDER = "openHumans"

fun Application.openHumansModule() {
    val clientId = getStringProperty("gateway.openHumans.clientId")
    val clientSecret = getStringProperty("gateway.openHumans.clientSecret")
    val oAuthSettings = OAuthServerSettings.OAuth2ServerSettings(
        name = OPEN_HUMANS_AUTH_PROVIDER,
        authorizeUrl = OpenHumansAPI.AUTHORIZE_URL,
        accessTokenUrl = OpenHumansAPI.ACCESS_TOKEN_URL,
        clientId = clientId,
        clientSecret = clientSecret,
        requestMethod = HttpMethod.Post,
        accessTokenRequiresBasicAuth = true
    )
    install(Authentication) {
        oauth(OPEN_HUMANS_AUTH_PROVIDER) {
            client = HttpClient(Apache)
            providerLookup = { oAuthSettings }
            urlProvider = { constructURL("/openhumans/login") }
        }
    }
    val openHumansAPI = OpenHumansAPI(clientId, clientSecret)
    this.openHumansApi = openHumansAPI
    routing {
        route("/openhumans") {
            get("/") {
                if (call.participant == null) {
                    call.respondRedirect("/")
                } else {
                    call.respondOpenHumansTemplate()
                }
            }
            route("/login") {
                authenticate(OPEN_HUMANS_AUTH_PROVIDER) {
                    handle {
                        val principal = call.authentication.principal<OAuthAccessTokenResponse.OAuth2>()
                        if (principal?.refreshToken != null) {
                            val participant = call.participant
                            val expiresAt = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(principal.expiresIn)
                            val memberInfo = openHumansAPI.getProjectMemberInfo(principal.accessToken)
                            if (participant == null) {
                                val ohParticipant = this@openHumansModule.database.getParticipantByProjectMemberId(memberInfo.projectMemberId)
                                if (ohParticipant != null) {
                                    this@openHumansModule.database.setOpenHumansTokensForParticipant(
                                        ohParticipant.id,
                                        principal.accessToken,
                                        principal.refreshToken!!,
                                        expiresAt,
                                        memberInfo.projectMemberId
                                    )
                                    val (sessionId, _, sessionSecret) = this@openHumansModule.database.createSession(ohParticipant.id)
                                    val currentSession = call.sessions.get<GatewaySession>() ?: GatewaySession()
                                    call.sessions.set(currentSession.copy(sessionId = sessionId, sessionSecret = sessionSecret))
                                    call.respondRedirect("/todos")
                                } else {
                                    call.respondNoParticipantFoundTemplate()
                                }
                            } else {
                                if (participant.projectMemberId == null) {
                                    if (this@openHumansModule.database.getParticipantByProjectMemberId(memberInfo.projectMemberId) == null) {
                                        this@openHumansModule.database.setOpenHumansTokensForParticipant(
                                            participant.id,
                                            principal.accessToken,
                                            principal.refreshToken!!,
                                            expiresAt,
                                            memberInfo.projectMemberId
                                        )
                                        call.respondOpenHumansSuccessTemplate(memberInfo.sourcesOfData)
                                    } else {
                                        call.respondAccountAlreadyLinkedTemplate()
                                    }
                                } else {
                                    if (participant.projectMemberId == memberInfo.projectMemberId) {
                                        this@openHumansModule.database.setOpenHumansTokensForParticipant(
                                            participant.id,
                                            principal.accessToken,
                                            principal.refreshToken!!,
                                            expiresAt,
                                            memberInfo.projectMemberId
                                        )
                                        call.respondOpenHumansSuccessTemplate(memberInfo.sourcesOfData.toSet())
                                    } else {
                                        call.respondWrongAccountTemplate()
                                    }
                                }
                            }
                        } else {
                            call.respondAuthorizeErrorTemplate()
                        }
                    }
                }
            }
        }
    }
}
