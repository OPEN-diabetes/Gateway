package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.templates.respondTODOsTemplate
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.*
import io.ktor.client.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.*

fun Application.todosModule() {
    routing {
        get("/todos") {
            val participant = call.participant
            if (participant != null) {
                val sharedSources = async(SupervisorJob(coroutineContext[Job])) {
                    if (participant.projectMemberId != null) {
                        try {
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
                            this@todosModule.openHumansApi.getProjectMemberInfo(accessToken).sourcesOfData
                        } catch (e: ClientRequestException) {
                            when (e.response?.status) {
                                HttpStatusCode.Unauthorized, HttpStatusCode.BadRequest -> null
                                else -> throw e
                            }
                        }
                    } else {
                        null
                    }
                }
                val hasCompletedFollowupSurvey = async(SupervisorJob(coroutineContext[Job])) {
                    if (participant.followupSurveyRecordId != null) {
                        this@todosModule.followupRedCapAPI.hasCompletedSurvey(participant.followupSurveyRecordId)
                    } else {
                        false
                    }
                }
                val hcpLink = async(SupervisorJob(coroutineContext[Job])) {
                    if (participant.enrollmentType in arrayOf(
                            EnrollmentType.ADULT_USING_DIYAPS,
                            EnrollmentType.ADULT_NOT_USING_DIYAPS
                        )
                    ) {
                        this@todosModule.database.getHcpLinkForParticipant(participant.id)
                    } else {
                        null
                    }
                }
                call.respondTODOsTemplate(
                    participantId = formatParticipantId(participant.id, participant.secret),
                    dataSources = sharedSources.await(),
                    showFollowupSurvey = participant.enrollmentType in arrayOf(
                        EnrollmentType.ADULT_USING_DIYAPS, EnrollmentType.ADULT_NOT_USING_DIYAPS,
                        EnrollmentType.PARENT_USING_DIYAPS, EnrollmentType.PARENT_NOT_USING_DIYAPS
                    ) && participant.surveyRecordId == null,
                    hasFilledOutFollowupSurvey = hasCompletedFollowupSurvey.await(),
                    showHCPSurvey = participant.enrollmentType in arrayOf(
                        EnrollmentType.ADULT_USING_DIYAPS,
                        EnrollmentType.ADULT_NOT_USING_DIYAPS
                    ) && (participant.surveyRecordId != null || hasCompletedFollowupSurvey.await()),
                    hcpUrl = hcpLink.await()?.let { call.constructURL("/hcp/${it.id}/${it.secret}") }
                )
            } else {
                call.respondRedirect("/")
            }
        }
    }
}
