package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.database.ParticipationLink
import eu.opendiabetes.gateway.templates.ParticipationLinkTODO
import eu.opendiabetes.gateway.templates.respondTODOsTemplate
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.client.features.ClientRequestException
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

fun Application.todosModule() {
    routing {
        get("/todos") {
            val participant = call.participant
            if (participant != null) {
                val surveyLinks = async {
                    this@todosModule.database.getSurveyLinksForParticipant(participant.id)
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
                val participationLinks = surveyLinks.await().map {
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
                }
                val sharedSources = try {
                    memberInfo?.await()?.sourcesOfData
                } catch (e: ClientRequestException) {
                    if (e.response?.status == HttpStatusCode.Unauthorized) {
                        null
                    } else {
                        throw e
                    }
                }
                call.respondTODOsTemplate(
                    formatParticipantId(participant.id, participant.secret),
                    participationLinks,
                    sharedSources
                )
            } else {
                call.respondRedirect("/")
            }
        }
    }
}

private fun ApplicationCall.constructParticipationURL(participationLink: ParticipationLink): String {
    return constructURL("/p/${participationLink.id}/${participationLink.secret}")
}
