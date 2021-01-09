package eu.opendiabetes.gateway.modules

import eu.opendiabetes.gateway.database.EnrollmentType
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_ADULT_NON_USERS
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_ADULT_USERS
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_PARENT_NON_USERS
import eu.opendiabetes.gateway.language.info_sheets.english.INFO_SHEET_PARENT_USERS
import eu.opendiabetes.gateway.templates.respondConsentNotGivenTemplate
import eu.opendiabetes.gateway.templates.respondConsentTemplate
import eu.opendiabetes.gateway.utils.database
import eu.opendiabetes.gateway.utils.participant
import eu.opendiabetes.gateway.utils.setInformationSheetShown
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.updateConsentModule() {
    routing {
        intercept(ApplicationCallPipeline.Call) {
            val participant = call.participant
            when {
                call.request.path().startsWith("/static/") -> proceed()
                participant == null -> when {
                    call.request.path() == "/update_consent" -> {
                        call.respondRedirect("/")
                        finish()
                    }
                    else -> proceed()
                }
                (participant.enrollmentType == EnrollmentType.ADULT_USING_DIYAPS ||
                        participant.enrollmentType == EnrollmentType.ADULT_NOT_USING_DIYAPS ||
                        participant.enrollmentType == EnrollmentType.PARENT_USING_DIYAPS ||
                        participant.enrollmentType == EnrollmentType.PARENT_NOT_USING_DIYAPS) &&
                        !participant.informationSheetShown -> when {
                    call.request.path() == "/update_consent" -> {
                        proceed()
                    }
                    else -> {
                        call.respondRedirect("/update_consent")
                        finish()
                    }
                }
                else -> when {
                    call.request.path() == "/update_consent" -> {
                        call.respondRedirect("/todos")
                        finish()
                    }
                    else -> proceed()
                }
            }
        }
        route("update_consent") {
            get("/") {
                val infoSheet = when (call.participant!!.enrollmentType) {
                    EnrollmentType.ADULT_USING_DIYAPS -> INFO_SHEET_ADULT_USERS
                    EnrollmentType.ADULT_NOT_USING_DIYAPS -> INFO_SHEET_ADULT_NON_USERS
                    EnrollmentType.PARENT_USING_DIYAPS -> INFO_SHEET_PARENT_USERS
                    EnrollmentType.PARENT_NOT_USING_DIYAPS -> INFO_SHEET_PARENT_NON_USERS
                    else -> throw IllegalStateException("Enrollment type doesn't require consent update")
                }
                call.respondConsentTemplate("/update_consent", infoSheet)
            }
            post("/") {
                if (call.receiveParameters().contains("consent")) {
                    this@updateConsentModule.database.setInformationSheetShown(call.participant!!.id)
                    call.respondRedirect("/todos")
                } else {
                    call.respondConsentNotGivenTemplate("/update_consent")
                }
            }
        }
    }
}
