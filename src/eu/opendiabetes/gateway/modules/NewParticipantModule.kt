package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.GatewaySession
import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.templates.*
import eu.opendiabetes.gateway.utils.createParticipantId
import eu.opendiabetes.gateway.utils.createSession
import eu.opendiabetes.gateway.utils.database
import eu.opendiabetes.gateway.utils.session
import io.ktor.application.*
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import io.ktor.util.pipeline.PipelineContext

fun Application.newParticipantModule() {
    routing {
        route("new_participant") {
            intercept(ApplicationCallPipeline.Call) {
                if (call.session != null) {
                    call.respondRedirect("/todos")
                    finish()
                } else {
                    proceed()
                }
            }
            get("/") {
                call.respondNewParticipantTemplate()
            }
            route("/adult") {
                get("/") {
                    call.respondAdultIsUsingAPSTemplate()
                }
                route("/using_diyaps") {
                    get("/") {
                        call.respondConsentTemplate()
                    }
                    post("/") {
                        if (call.receiveParameters().contains("consent")) {
                            createParticipantIdAndRedirect(EnrollmentType.ADULT_USING_DIYAPS)
                        } else {
                            call.respondConsentNotGivenTemplate("/new_participant/adult/using_diyaps")
                        }
                    }
                }
                route("/not_using_diyaps") {
                    get("/") {
                        call.respondConsentTemplate()
                    }
                    post("/") {
                        if (call.receiveParameters().contains("consent")) {
                            createParticipantIdAndRedirect(EnrollmentType.ADULT_NOT_USING_DIYAPS)
                        } else {
                            call.respondConsentNotGivenTemplate("/new_participant/adult/not_using_diyaps")
                        }
                    }
                }
            }
            route("/parent") {
                get("/") {
                    call.respondParentIsUsingAPSTemplate()
                }
                route("/using_diyaps") {
                    get("/") {
                        call.respondConsentTemplate()
                    }
                    post("/") {
                        if (call.receiveParameters().contains("consent")) {
                            createParticipantIdAndRedirect(EnrollmentType.PARENT_USING_DIYAPS)
                        } else {
                            call.respondConsentNotGivenTemplate("/new_participant/parent/using_diyaps")
                        }
                    }
                }
                route("/not_using_diyaps") {
                    get("/") {
                        call.respondConsentTemplate()
                    }
                    post("/") {
                        if (call.receiveParameters().contains("consent")) {
                            createParticipantIdAndRedirect(EnrollmentType.PARENT_NOT_USING_DIYAPS)
                        } else {
                            call.respondConsentNotGivenTemplate("/new_participant/parent/not_using_diyaps")
                        }
                    }
                }
            }
            get("/teenager") {
                call.respondAskParentForLinkTemplate()
            }
            get("/partner") {
                call.respondAskPartnerForLinkTemplate()
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.createParticipantIdAndRedirect(enrollmentType: EnrollmentType) {
    val participant = application.database.createParticipantId(enrollmentType)
    val (sessionId, _, sessionSecret) = application.database.createSession(participant.id)
    val currentSession = call.sessions.get<GatewaySession>() ?: GatewaySession()
    call.sessions.set(currentSession.copy(sessionId = sessionId, sessionSecret = sessionSecret))
    call.respondRedirect("/present_id")
}
