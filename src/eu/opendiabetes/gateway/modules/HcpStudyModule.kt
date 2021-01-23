package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_HCP_STUDY_PARTICIPANTS
import eu.opendiabetes.gateway.templates.respondConsentNotGivenTemplate
import eu.opendiabetes.gateway.templates.respondConsentTemplate
import eu.opendiabetes.gateway.utils.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

fun Application.hcpStudyModule() {
    routing {
        route("hcp-study") {
            intercept(ApplicationCallPipeline.Call) {
                val participant = call.participant
                val hasCompletedFollowupSurvey = async(SupervisorJob(coroutineContext[Job])) {
                    if (participant?.followupSurveyRecordId != null) {
                        this@hcpStudyModule.followupRedCapAPI.hasCompletedSurvey(participant.followupSurveyRecordId)
                    } else {
                        false
                    }
                }
                val hcpLink = async {
                    if (participant?.id != null) {
                        this@hcpStudyModule.database.getHcpLinkForParticipant(participant.id)
                    } else {
                        null
                    }
                }
                when {
                    participant == null ||
                            participant.enrollmentType !in arrayOf(
                        EnrollmentType.ADULT_USING_DIYAPS,
                        EnrollmentType.ADULT_NOT_USING_DIYAPS
                    ) ||
                            (participant.surveyRecordId == null && !hasCompletedFollowupSurvey.await()) -> {
                        call.respond(HttpStatusCode.NotFound)
                        finish()
                    }
                    hcpLink.await() != null -> {
                        call.respondRedirect("/todos")
                        finish()
                    }
                    else -> proceed()
                }
            }
            get("/") {
                call.respondConsentTemplate("/todos", INFO_SHEET_HCP_STUDY_PARTICIPANTS)
            }
            post("/") {
                if (call.receiveParameters().contains("consent")) {
                    this@hcpStudyModule.database.createHcpLink(call.participant!!.id)
                    call.respondRedirect("/todos")
                } else {
                    call.respondConsentNotGivenTemplate("/hcp-study")
                }
            }
        }
    }
}
