package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall

suspend fun ApplicationCall.respondNewParticipantTemplate() =
    respondBranchingTemplate(language.newParticipant, null, language.pleaseSelectWhichApplies, "/") {
        branchingOption("/static/adult.svg", language.iAmAnAdult, "/new_participant/adult")
        branchingOption("/static/parent.svg", language.iAmAParent, "/new_participant/parent")
    }


suspend fun ApplicationCall.respondParentIsUsingAPSTemplate() = respondBranchingTemplate(language.newParticipant, null, language.isYourChildUsingDIYAPS, "/new_participant") {
    branchingOption("/static/parent.svg", language.myChildIsUsingDIYAPS, "/new_participant/parent/using_diyaps")
    branchingOption("/static/parent.svg", language.myChildIsNotUsingDIYAPSAndDontIntend, "/new_participant/parent/not_using_diyaps")
}

suspend fun ApplicationCall.respondAdultIsUsingAPSTemplate() = respondBranchingTemplate(language.newParticipant, null, language.areYouUsingDIYAPS, "/new_participant") {
    branchingOption("/static/adult.svg", language.iAmUsingDIYAPS, "/new_participant/adult/using_diyaps")
    branchingOption("/static/adult.svg", language.iAmNotUsingDIYAPSAndDontIntend, "/new_participant/adult/not_using_diyaps")
}
