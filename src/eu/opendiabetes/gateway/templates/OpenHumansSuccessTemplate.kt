package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.OpenHumansAPI
import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondOpenHumansSuccessTemplate(dataSources: Set<String>) = respondBaseTemplate(language.openHumans, null) {
    div {
        id = "openhumans-success"
        p {
            b() {
                text(language.success)
            }
            br
            if (dataSources.isEmpty()) {
                text(language.linkToOpenHumansTextNoData)
            } else {
                text(language.linkToOpenHumansTextDataSources)
            }
        }
        if (dataSources.isEmpty()) {
            p {
                unsafe {
                    raw(language.ifYouDidAddAnyDataSources("https://www.openhumans.org/direct-sharing/projects/leave/18513/?next=/activity/open/", "/openhumans"))
                }
            }
        } else {
            ul {
                if (dataSources.contains(OpenHumansAPI.NIGHTSCOUT_ID)) li("success") { text(language.nightscoutDataTransfer) }
                if (dataSources.contains(OpenHumansAPI.ANDROIDAPS_ID)) li("success") { text(language.androidAPSUploader) }
                if (dataSources.contains(OpenHumansAPI.SELFIES_ID)) li("success") { text(language.dataSelfie) }
            }
            p {
                unsafe {
                    raw(language.missingDataSources("https://www.openhumans.org/direct-sharing/projects/leave/18513/?next=/activity/open/", "/openhumans"))
                }
            }
        }
        a("/") {
            id = "go-back"
            text(language.goBack)
        }
    }
}
