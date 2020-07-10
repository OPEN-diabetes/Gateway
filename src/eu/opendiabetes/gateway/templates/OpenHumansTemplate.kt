package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondOpenHumansTemplate() = respondBaseTemplate(language.openHumans, null) {
    div {
        id = "openhumans"
        img("", "/static/openhumans.svg") {
            id = "oh-logo"
        }
        ol {
            id = "oh-guide"
            li {
                text(language.createOpenHumansAccount)
                p {
                    text(language.signUpOnOpenHumans)
                }
                a("https://www.openhumans.org/account/signup/", classes = "button") {
                    text(language.signUp)
                }
            }
            li {
                text(language.uploadYourData)
                p {
                    text(language.chooseDataSource)
                }
                ul {
                    li {
                        text(language.nightscoutDataTransfer)
                        p {
                            text(language.nightscoutDataTransferDescription)
                        }
                        a("https://dataxfer-ns-oh.herokuapp.com/", "_blank", "button") {
                            text(language.setup)
                        }
                    }
                    li {
                        text(language.androidAPSUploader)
                        p {
                            text(language.androidAPSUploaderDescription)
                        }
                        a("https://github.com/openaps/AndroidAPSdocs/blob/dev/docs/EN/Where-To-Go-For-Help/OpenHumans.rst", "_blank", "button") {
                            text(language.instructions)
                        }
                    }
                    li {
                        text(language.dataSelfie)
                        p {
                            text(language.dataSelfieDescription)
                        }
                        a("https://dataselfie.openhumans.org/", "_blank", "button") {
                            text(language.takeASelfie)
                        }
                    }
                    li {
                        text(language.noData)
                        p {
                            text(language.noDataDescription)
                        }
                    }
                }
            }
            li {
                text(language.linkToOpenHumans)
                p {
                    text(language.linkToOpenHumansDescription)
                }
                a("/openhumans/login", classes = "button") {
                    text(language.authorize)
                }
            }
        }
        a("/todos") {
            id = "go-back"
            text(language.goBack)
        }
    }
}
