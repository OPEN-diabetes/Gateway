package eu.opendiabetes.gateway.templates

import eu.opendiabetes.gateway.utils.language
import io.ktor.application.ApplicationCall
import kotlinx.html.*

suspend fun ApplicationCall.respondConsentTemplate(
    backUrl: String? = null,
    informationSheet: String = testInformationSheet
) = respondBaseTemplate(
    language.declarationOfConsent,
    null
) {
    div {
        id = "consent-form"
        div {
            id = "consent-content"
            unsafe {
                raw(informationSheet)
            }
        }
        form(method = FormMethod.post) {
            input(InputType.checkBox, name = "consent") {
                id = "consent"
            }
            label {
                htmlFor = "consent"
                text(language.iUnderstandAndAgree)
            }
            input(InputType.submit)
        }
    }
    if (backUrl != null) {
        a(backUrl) {
            id = "go-back"
            text(language.goBack)
        }
    }
}

private val testInformationSheet = """
    <h1>Name of Study</h1>
    <p>OPEN (Outcomes of Patients’ Evidence with Novel, Do-it-Yourself Artificial Pancreas Technology)</p>
    <h1>What is research?</h1>
    <p>Research is a way to find out about something new.</p>
    <h1>Why are we doing this research?</h1>
    <p>We are trying to find out about the diabetes management systems people with diabetes have made.
        These are called do-it-yourself artificial pancreas or DIYAPS. We want to find out if they make the
        lives of people with diabetes and their families better.</p>
    <h1>Why have I been asked to take part?</h1>
    <p>You have been asked to take part as we think you are using a DIYAPS.</p>
    <h1>What will happen to me if I take part?</h1>
    <ol>
        <li>After you have read this information sheet, there will be a box to tick.  If you tick this box, it tells us you have understood this leaflet and that you would like to take part in the study.
            If you do not understand this leaflet, do not tick the box.
            If you do not want to take part, do not tick the box.
        </li>
        <li>
            If you tick the box, then we would like you to answer some questions.  The questions will be in an online survey.  The questions will ask about your life with diabetes. It should take about 10-20 minutes to answer all the questions.
        </li>
        <li>
            One of the adults who looks after you, mother, father or someone else, will also be asked to help.  We will ask them to give us some information from your insulin pump and blood sugar sensor.  They will get the data from your pump and sensor and upload into an online database called ‘Open Humans’. Other people who agree to take part in this study will also upload their data into the same place.  This means we can then look at everyone’s data to see what it tells us. The information that is saved, will be able to be used by other people who get permission.  If you do not want other people to see your data then please tell us. We will then remove the information you gave us straight away.
        </li>
    </ol>
    <h1>Do I have to take part?</h1>
    <p>No!<br>You do not have to take part if you don’t want to.<br>Please read this leaflet and talk to your parent(s) or carer(s) before you decide.
        If you don’t want to take part, you do not have to do anything
    </p>
    <h1>What will happen to me after I have filled out the survey?</h1>
    <p>Nothing will happen to you.<br>
        We will add your information to the information other people have given us. We will then look at this data and see what is tell us. When we have done that we will write about what we found for others to read.  We hope this information will be used to help people with diabetes in the future.
    </p>
    <h1>How do you protect my data/information?</h1>
    <p>When we at OPEN have finished looking at the information you gave us, we will share the data with other people. This is done by making the data publicly available online. Then researchers and members of the public can learn from it as well. To protect your privacy, any information that might be used to work out your name or other personal information about you will be deleted before it is shared with the public.
        <br><br>
        If you have any questions please get in contact and ask us.
    </p>
""".trimIndent()
