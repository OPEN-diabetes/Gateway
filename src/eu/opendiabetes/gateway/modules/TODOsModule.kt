package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.templates.respondTODOsTemplate
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.*
import io.ktor.client.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

fun Application.todosModule() {
    routing {
        get("/todos") {
            val participant = call.participant
            if (participant != null) {
                val hcpLink = when (participant.enrollmentType) {
                    EnrollmentType.ADULT_USING_DIYAPS, EnrollmentType.ADULT_NOT_USING_DIYAPS -> async {
                        this@todosModule.database.createHcpLinkForParticipantIfNeeded(participant.id)
                    }
                    else -> null
                }
                val memberInfo = if (participant.projectMemberId != null) {
                    async(SupervisorJob(coroutineContext[Job])) {
                        val accessToken = if (participant.expiresAt!! <= System.currentTimeMillis()) {
                            val (accessToken, refreshToken, expiresAt) = this@todosModule.openHumansApi.refreshAccessToken(
                                participant.refreshToken!!
                            )
                            this@todosModule.database.setOpenHumansTokensForParticipant(
                                participant.id,
                                accessToken,
                                refreshToken,
                                expiresAt
                            )
                            accessToken
                        } else {
                            participant.accessToken!!
                        }
                        this@todosModule.openHumansApi.getProjectMemberInfo(accessToken)
                    }
                } else {
                    null
                }
                /*val participationLinks = surveyLinks.await().map {
                    when (it.enrollmentType) {
                        EnrollmentType.PARENT_USING_DIYAPS -> ParticipationLinkTODO.Parent(
                            call.constructParticipationURL(
                                it
                            )
                        )
                        EnrollmentType.TEENAGER_USING_DIYAPS -> ParticipationLinkTODO.Child(
                            call.constructParticipationURL(
                                it
                            )
                        )
                        EnrollmentType.PARTNER_USING_DIYAPS -> ParticipationLinkTODO.Partner(
                            call.constructParticipationURL(
                                it
                            )
                        )
                        EnrollmentType.PARTNER_NOT_USING_DIYAPS -> ParticipationLinkTODO.Partner(
                            call.constructParticipationURL(
                                it
                            )
                        )
                        else -> throw IllegalArgumentException("Invalid enrollmentType")
                    }
                }*/
                val sharedSources = try {
                    memberInfo?.await()?.sourcesOfData
                } catch (e: ClientRequestException) {
                    when (e.response?.status) {
                        HttpStatusCode.Unauthorized, HttpStatusCode.BadRequest -> null
                        else -> throw e
                    }
                }
                call.respondTODOsTemplate(
                    formatParticipantId(participant.id, participant.secret),
                    sharedSources,
                    participant.surveyRecordId == null,
                    hcpLink?.await()?.let { link -> call.constructURL("/hcp/${participant.id}/${link.secret}") }
                )
            } else {
                call.respondRedirect("/")
            }
        }
    }
}
