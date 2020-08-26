package eu.opendiabetes.gateway

val LANGUAGES = Language::class.sealedSubclasses.mapNotNull { it.objectInstance }
val DEFAULT_LANGUAGE = English

sealed class Language {
    abstract val code: String
    abstract val name: String
    abstract val gdpr: String
    abstract val legalNotice: String
    abstract val genderNotice: String?
    abstract val hiThere: String
    abstract val thanksForHelpingUs: String
    abstract val submit: String
    abstract val unknownId: String
    abstract val newParticipant: String
    abstract val pleaseSelectWhichApplies: String
    abstract val iAmAnAdult: String
    abstract val iAmAParent: String
    abstract val iAmATeenager: String
    abstract val iAmAPartner: String
    abstract val goBack: String
    abstract val areYouUsingDIYAPS: String
    abstract val iAmUsingDIYAPS: String
    abstract val iAmNotUsingDIYAPSAndDontIntend: String
    abstract val isYourChildUsingDIYAPS: String
    abstract val myChildIsUsingDIYAPS: String
    abstract val myChildIsNotUsingDIYAPSAndDontIntend: String
    abstract val iAmNotUsingADIYAPS: String
    abstract val childNotUsingDIYAPS: String
    abstract val notUsingDIYAPS: String
    abstract val yourPartnerHasDiabetes: String
    abstract val childHasAlreadyParticipated: String
    abstract val youAreATeenagerWithDiabetesAndWantToParticipate: String
    abstract val didYourParentAlreadyFillOutTheSurvey: String
    abstract val didYourChildAlreadyFillOutTheSurvey: String
    abstract val iAmNewToThisSurvey: String
    abstract val weAreNewToThisSurvey: String
    abstract val myParentHasAlreadyFilledOutTheSurvey: String
    abstract val myChildHasAlreadyFilledOutTheSurvey: String
    abstract val notWithinAudience: String
    abstract val askChildForParticipationLink: String
    abstract val askParentForParticipationLink: String
    abstract val askPartnerForParticipationLink: String
    abstract val askPatientForParticipationLink: String
    abstract val yourParticipantIdIs: String
    abstract val pleaseNoteItDownToAvoidLosingIt: String
    abstract val proceed: String
    abstract val yourTODOs: String
    abstract val askParent: String
    abstract val askChild: String
    abstract val askPartner: String
    abstract val sendParent: String
    abstract val sendChild: String
    abstract val sendPartner: String
    abstract val fillOutSurvey: String
    abstract val answerAFewQuestions: String
    abstract val goToSurvey: String
    abstract val signOut: String
    abstract val linkToOpenHumans: String
    abstract val linkToOpenHumans2: String
    abstract val linkToOpenHumansTextSetup: String
    abstract val linkToOpenHumansTextNoData: String
    abstract val linkToOpenHumansTextDataSources: String
    abstract val androidAPSUploader: String
    abstract val nightscoutDataTransfer: String
    abstract val dataSelfie: String
    abstract val setup: String
    abstract val openHumans: String
    abstract val createOpenHumansAccount: String
    abstract val signUpOnOpenHumans: String
    abstract val uploadYourData: String
    abstract val chooseDataSource: String
    abstract val nightscoutDataTransferDescription: String
    abstract val androidAPSUploaderDescription: String
    abstract val dataSelfieDescription: String
    abstract val noData: String
    abstract val noDataDescription: String
    abstract val linkToOpenHumansDescription: String
    abstract val authorize: String
    abstract val instructions: String
    abstract val takeASelfie: String
    abstract val signUp: String
    abstract val accountAlreadyLinked: String
    abstract val accountAlreadyLinkedDescription: String
    abstract val wrongAccount: String
    abstract val pleaseReuseAccount: String
    abstract val success: String
    abstract val somethingWentWrong: String
    abstract val couldntConnectToOpenHumans: String
    abstract val noParticipantIdFound: String
    abstract val orSignInUsingOpenHumans: String
    abstract val setupAgain: String
    abstract val declarationOfConsent: String
    abstract val iUnderstandAndAgree: String
    abstract val consentNotGiven: String
    abstract val consentNotGivenDescription: String
    abstract val doYouAlreadyHaveAParticipantId: String
    abstract val iAlreadyHaveAParticipantId: String
    abstract val iDoNotHaveAParticipantId: String
    abstract val introduction: String
    abstract val ohLoginNotice: String
    abstract val loginText: String
    abstract val login: String
    abstract val invitationToSurvey: String
    abstract val fundingNotice: String

    abstract fun participantId(participantId: String): String
    abstract fun missingDataSources(removeLink: String, tryAgain: String): String
    abstract fun ifYouDidAddAnyDataSources(removeLink: String, tryAgain: String): String
    abstract fun createNewParticipantID(href: String): String
    abstract fun invitationText(link: String): String
}

object English : Language() {
    override val code = "en"
    override val name = "English"
    override val gdpr = "GDPR"
    override val legalNotice = "Legal Notice"
    override val genderNotice: String? = null
    override val hiThere = "Welcome to the OPEN project!"
    override val thanksForHelpingUs =
        "Thank you for your interest and willingness to participate in our survey!"
    override val submit = "Submit"
    override val unknownId = "This Participant ID does not exist."
    override val newParticipant = "New Participant"
    override val pleaseSelectWhichApplies = "Please select which of the following applies to you:"
    override val iAmAnAdult = "I am an <b>adult (18+)</b> with diabetes."
    override val iAmAParent = "I am a <b>parent</b> or <b>caregiver</b> of a child with diabetes."
    override val iAmATeenager = "I am a <b>teenager (13 - 17)</b> with diabetes."
    override val iAmAPartner = "I have a <b>partner</b> who has diabetes."
    override val goBack = "Go back"
    override val areYouUsingDIYAPS = "Are you using a DIYAPS?"
    override val iAmUsingDIYAPS = "I am currently <b>using</b> a DIYAPS (open- or closed-loop)."
    override val iAmNotUsingDIYAPSAndDontIntend = "I am currently <b>not using</b> a DIYAPS."
    override val isYourChildUsingDIYAPS = "Is your child using a DIYAPS?"
    override val myChildIsUsingDIYAPS = "My child is currently <b>using</b> a DIYAPS (open- or closed-loop)."
    override val myChildIsNotUsingDIYAPSAndDontIntend =
        "My child is currently <b>not using</b> a DIYAPS."
    override val iAmNotUsingADIYAPS = "I am currently <b>not using</b> a DIYAPS."
    override val childNotUsingDIYAPS = "Your child is not using a DIYAPS."
    override val notUsingDIYAPS = "You are not using a DIYAPS."
    override val yourPartnerHasDiabetes = "Your partner has diabetes and you want to participate in our study?"
    override val childHasAlreadyParticipated = ""
    override val youAreATeenagerWithDiabetesAndWantToParticipate =
        "You are a teenager with diabetes and want to participate?"
    override val didYourParentAlreadyFillOutTheSurvey = "Did your parent already fill out the survey?"
    override val didYourChildAlreadyFillOutTheSurvey = ""
    override val iAmNewToThisSurvey = ""
    override val weAreNewToThisSurvey = "We are new to this survey."
    override val myParentHasAlreadyFilledOutTheSurvey = "My parent has already filled out the survey."
    override val myChildHasAlreadyFilledOutTheSurvey = ""
    override val notWithinAudience =
        "Thank you very much for your interest in this study! We are very sorry that we don´t need your help at the moment, but we may come back to you later."
    override val askChildForParticipationLink = ""
    override val askParentForParticipationLink =
        "We’re sorry, but due to legal reasons, you need permission from your parent(s)/caregiver(s) to participate in this survey. Therefore, please ask them for a participation link that they can get after signing up on their own."
    override val askPartnerForParticipationLink = "Please ask your partner to send you a participation link."
    override val askPatientForParticipationLink = "Please ask your patient to send you a participation link."
    override val yourParticipantIdIs = "Your Participant ID is:"
    override val pleaseNoteItDownToAvoidLosingIt =
        "The purpose of this ID is to guarantee anonymity in the survey. Should you need to take a break, you can continue where you have left off by noting the ID down, taking a picture of it or emailing it to yourself to keep it safe."
    override val proceed = "Proceed"
    override val yourTODOs = "How you can help"
    override val askParent = ""
    override val askChild = "Ask your child to also fill out the survey"
    override val askPartner = "Ask your partner, if you have one, to complete a shorter version of the survey"
    override val sendParent = ""
    override val sendChild = "Please send the following participation link to your child, e.g. via e-mail:"
    override val sendPartner = "Please send the following participation link to your partner, e.g. via e-mail: It should not take them any longer than 10-15 minutes and responses will be confidential. That means you will not be able to see your partner´s responses and vice-versa."
    override val fillOutSurvey = "Participate in the OPEN survey"
    override val answerAFewQuestions =
        "We kindly invite you to answer a few questions if you like. This will take no longer than 20 to 30 minutes."
    override val goToSurvey = "Go to survey"
    override val signOut = "Sign out"
    override val linkToOpenHumans = "Participate in future follow-up studies and/or donate your device data via Open Humans"
    override val linkToOpenHumans2 = "Link to Open Humans"
    override val linkToOpenHumansTextSetup =
        "OPEN has built a platform on the non-profit data repository site Open Humans. If you provide your link to Open Humans, we will use it to contact you about follow-up studies conducted by OPEN. If you like, you can also donate your diabetes device data to help our research efforts in improving APS algorithms. This is voluntary. Click below for a step-by-step guide on how to do this."
    override val linkToOpenHumansTextNoData =
        "Your Open Humans account has been connected, however, no data sources have been detected."
    override val linkToOpenHumansTextDataSources =
        "Your Open Humans account has been connected. The following data sources were detected:"
    override val androidAPSUploader = "AndroidAPS Uploader"
    override val nightscoutDataTransfer = "Nightscout Data Transfer"
    override val dataSelfie = "Data Selfie"
    override val setup = "Setup"
    override val openHumans = "Open Humans"
    override val createOpenHumansAccount = "Create an Open Humans account"
    override val signUpOnOpenHumans =
        "Sign up for an account on openhumans.org if you do not have one yet. You can re-use your existing Facebook or Google login."
    override val uploadYourData = "Upload your data <i>(note: if you are signing up to Open Humans so we can contact you about future studies, you may skip step 2 and go straight to step3)</i>"
    override val chooseDataSource = "The following data sources are available:"
    override val nightscoutDataTransferDescription =
        "If you are already uploading your data to Nightscout, use this simple tool to copy your data over to Open Humans."
    override val androidAPSUploaderDescription =
        "Nightscout is not an option? You can directly upload your data from within AndroidAPS."
    override val dataSelfieDescription =
        "None of the above work out for you? The Data Selfie tool allows you to upload any data you want, e.g. an export from Dexcom Clarity or another software of your choice."
    override val noData = "I do not want to upload my data"
    override val noDataDescription =
        "This is also fine. Open Humans still allows us to contact you anonymously. You can come back to this site at any time if you have changed your mind."
    override val linkToOpenHumansDescription =
        "One last step to finally establish the link to your Open Humans account. You have to redo this step if you decide to add a data source in the future."
    override val authorize = "Authorize"
    override val instructions = "Instructions"
    override val takeASelfie = "Take a Selfie"
    override val signUp = "Sign up"
    override val accountAlreadyLinked = "Account already linked"
    override val accountAlreadyLinkedDescription =
        "This Open Humans account is already linked to another Participant ID."
    override val wrongAccount = "Wrong account"
    override val pleaseReuseAccount =
        "Please reuse the account that you have already used in the past to link to your Participant ID."
    override val success = "Success!"
    override val somethingWentWrong = "Something went wrong"
    override val couldntConnectToOpenHumans = "We could not connect to your Open Humans account."
    override val noParticipantIdFound = "No Participant ID found"
    override val orSignInUsingOpenHumans = "Or sign in using Open Humans"
    override val setupAgain = "Setup again"
    override val declarationOfConsent = "Declaration of Consent"
    override val iUnderstandAndAgree = "I understand and agree."
    override val consentNotGiven = "Consent not given"
    override val consentNotGivenDescription =
        "Thank you for your interest in this study, however, you cannot proceed without giving your consent."
    override val doYouAlreadyHaveAParticipantId = "Do you already have a Participant ID?"
    override val iAlreadyHaveAParticipantId = "I already have a Participant ID."
    override val iDoNotHaveAParticipantId = "I do not have a Participant ID."
    override val introduction = """
        <p>We are the OPEN project, a European Union-funded international research consortium aiming to study and explore the unique initiative of “Do-It-Yourself Artificial Pancreas Systems”, or DIYAPS. This technology has been developed for better diabetes management by people with type 1 diabetes and their families. Some people consider it a revolutionary innovation in the history of medicine, others are sceptical as this is still “DIY”...</p>
        <p>We believe that the online community of people interested in DIYAPS provides an excellent opportunity for researchers to examine the effects of using DIYAPS on health and well-being, to identify potential technological improvements that can be made to artificial pancreas technology, to explore the difficulties and challenges that people face in building DIYAPS and reasons why some choose not to use DIYAPS.</p>
        <p>There is no development without research, so why not help us build evidence? We need the involvement of <b>everyone</b> from the diabetes online community. This includes DIYAPS users and non-users from all over the world, as well as their families and partners.</p>
        <h1>How can YOU help?</h1>
        <p>To remain anonymous, you will not give your real name. Instead, a participant ID will be automatically generated for each of you before you start the survey.</p>
        <p>After that, we mainly need you to answer questions about your health and well-being, your diabetes and, if applicable, your experience with DIYAPS and/or opinion on challenges related to DIYAPS use. <b>All data will be kept anonymous.</b></p>
        <p>If you are a DIYAPS user, you will also have the option to anonymously donate your loop device data (e.g. from Nightscout). This will require some additional effort on your part but the donated data would help greatly towards our aim to explore future improvements of APS algorithms.</p>
        <p>All of this is fully optional and voluntary! You decide how much information you want to provide. It should not take you more than <b>30 minutes</b> to complete.</p>
        <p>More detailed information and instructions will be provided on the next pages before you enter the survey. Further information about OPEN is also available <a href="https://open-diabetes.eu">here</a> on our website.</p>
        <p><b>Last but not least: We are very happy to have all of you here! THANK YOU!</b></p>
        <p><b>The OPEN team</b></p>
    """.trimIndent()
    override val ohLoginNotice = "(This only works if you have linked your Participant ID with Open Humans before.)"
    override val loginText = "To proceed please type in your Participant ID:"
    override val login = "Login"
    override val invitationToSurvey = "Invitation to OPEN Survey"
    override val fundingNotice = "This project has received funding from the European Commission's Horizon 2020 Research and Innovation Programme under the Marie Sklodowska-Curie Action Research and Innovation Staff Exchange (RISE) grant agreement number 823902."

    override fun participantId(participantId: String) = "Participant ID: <b>$participantId</b>"

    override fun missingDataSources(removeLink: String, tryAgain: String) =
        """In case the list of data sources appears to be incomplete, please <a href="$tryAgain">try again</a>. If you have recently setup a new data source, it might take a while to be detected."""

    override fun ifYouDidAddAnyDataSources(removeLink: String, tryAgain: String) =
        """If you did add any data sources, please <a target="_blank" href="$removeLink">click here</a> to remove the link and <a href="$tryAgain">try again</a>. If you have recently setup a new data source, it might take a while to be detected."""

    override fun createNewParticipantID(href: String) =
        """No Participant ID affiliated to this Open Humans account has been found. Either <a href="$href">create a new one</a> or try to sign in directly using your existing Participant ID."""


    override fun invitationText(link: String) = """
        Hi,

        I have participated in the OPEN survey and I would like you to take part in this, too. It will only take a few minutes but help the OPEN team in doing their research on the effectiveness of Do-It-Yourself Artificial Pancreas Systems (DIYAPS). Please follow this link to get to survey:

        $link

        The OPEN research team wants to find out how well people with diabetes are doing when they use or don´t use an open source loop (DIYAPS), if and how they are struggling to build these systems, how the technology can be improved, and they want to investigate if there are ways to give more people access to this technology in general. You can get further information about OPEN and this study on their website: www.open-diabetes.eu

        Best regards
    """.trimIndent()
}

/*object German : Language() {
    override val code = "de"
    override val name = "Deutsch"
    override val gdpr = "DSGVO"
    override val legalNotice = "Impressum"
    override val genderNotice = "Der Einfachheit halber wird immer nur die männliche Form genannt. Es sind aber immer alle Geschlechter gemeint!"
    override val hiThere = "Willkommen beim OPEN Projekt!"
    override val thanksForHelpingUs = "Danke, dass du bei unserem Projekt unterstützen möchtest, indem du an dieser Umfrage teilnimmst!"
    override val submit = "Senden"
    override val unknownId = "Diese Teilnehmer-ID existiert nicht."
    override val newParticipant = "Neuer Teilnehmer"
    override val pleaseSelectWhichApplies = "Bitte wähle das für dich Zutreffende aus:"
    override val iAmAnAdult = "Ich bin ein <b>Erwachsener</b> mit Diabetes."
    override val iAmAParent = "Ich bin <b>Elternteil</b> eines Kindes mit Diabetes."
    override val iAmATeenager = "Ich bin ein <b>Jugendlicher</b> mit Diabetes."
    override val iAmAPartner = "Ich habe einen <b>Partner</b>, der Diabetes hat."
    override val goBack = "Zurück"
    override val areYouUsingDIYAPS = "Verwendest du ein DIYAPS?"
    override val iAmUsingDIYAPS = "Ja, ich <b>verwende</b> momentan ein DIYAPS (open- oder closed-loop)."
    override val iAmNotYetUsingDIYAPSButIntend = ""
    override val iAmNotUsingDIYAPSAndDontIntend = "Nein, ich verwende momentan <b>kein</b> DIYAPS."
    override val isYourChildUsingDIYAPS = "Vewendet dein Kind ein DIYAPS?"
    override val myChildIsUsingDIYAPS = "Ja, mein Kind <b>verwendet</b> momentan ein DIYAPS (open- oder closed-loop)."
    override val myChildIsNotYetUsingDIYAPSButIntend = ""
    override val myChildIsNotUsingDIYAPSAndDontIntend = "Nein, mein Kind verwendet momentan <b>kein DIYAPS</b>."
    override val iAmNotUsingADIYAPS = "Nein, im Moment benutze ich (noch) <b>kein DIYAPS</b>."
    override val childNotUsingDIYAPS = "Dein Kind verwendet kein DIYAPS."
    override val notUsingDIYAPS = "Du benutzt kein DIYAPS."
    override val yourPartnerHasDiabetes = "Dein Partner hat Diabetes, und du möchtest (auch) an unserer Befragung teilnehmen?"
    override val childHasAlreadyParticipated = ""
    override val parentHasAlreadyParticipated = "Ein Elternteil hat bereits teilgenommen und nun möchtest du auch mitmachen?"
    override val didYourParentAlreadyFillOutTheSurvey = "Hat ein Elternteil die Umfrage bereits ausgefüllt?"
    override val didYourChildAlreadyFillOutTheSurvey = "Hat dein Kind die Umfrage bereits ausgefüllt?"
    override val iAmNewToThisSurvey = "Ich bin neu bei dieser Umfrage."
    override val weAreNewToThisSurvey = "Wir sind neu bei dieser Umfrage."
    override val myParentHasAlreadyFilledOutTheSurvey = "Ein Elternteil hat die Umfrage bereits ausgefüllt."
    override val myChildHasAlreadyFilledOutTheSurvey = ""
    override val notWithinAudience = "Vielen Dank für dein Interesse an dieser Studie! Deine Hilfe wird im Moment noch nicht benötigt, aber wir kommen vielleicht später auf dich zurück."
    override val askChildForParticipationLink = ""
    override val askParentForParticipationLink = "Bitte frage dein Elternteil nach einem Teilnahme-Link."
    override val askPartnerForParticipationLink = "Bitte frage deinen Partner nach einem Teilnahme-Link."
    override val askPatientForParticipationLink = "Bitte frage deinen Patienten nach einem Teilnahme-Link."
    override val yourParticipantIdIs = "Deine Teilnehmer-ID lautet:"
    override val pleaseNoteItDownToAvoidLosingIt = "Sie soll nur dir bekannt sein. Bitte schreibe sie dir auf, um sie nicht zu vergessen, und bewahre sie an einem nur dir zugänglichen Ort auf."
    override val proceed = "Fortfahren"
    override val yourTODOs = "Wie du uns helfen kannst"
    override val askParent = "Bitte ein Elternteil, die Umfrage ebenfalls auszufüllen"
    override val askChild = "Bitte dein Kind, die Umfrage ebenfalls auszufüllen"
    override val askPartner = "Bitte deinen Partner, die Umfrage ebenfalls auszufüllen"
    override val sendParent = ""
    override val sendChild = "Sende dazu den folgendenen Teilnahme-Link an dein Kind:"
    override val sendPartner = "Sende dazu den folgendenen Teilnahme-Link an deinen Partner:"
    override val fillOutSurvey = "An der Umfrage teilnehmen"
    override val answerAFewQuestions = "Bitte beantworte uns ein paar Fragen, wenn du möchtest. Dies wird nicht mehr als 20 bis 30 Minuten in Anspruch nehmen."
    override val goToSurvey = "Weiter zur Umfrage"
    override val signOut = "Abmelden"
    override val linkToOpenHumans = "Zusätzlich: Mit Open Humans verknüpfen (optional)"
    override val linkToOpenHumansTextSetup = "Bitte überlege, ob du uns zusätzlich helfen möchtest, weitere Nachweise über die Wirksamkeit von DIYAPS zu erhalten, indem du Open Humans beitrittst. Dies sollte nicht mehr als 20-30 Minuten in Anspruch nehmen. Open Humans ist eine nicht-kommerzielle Citizen Science Plattform, auf der man seine Daten anonym hochladen und selbst nutzen oder für die Forschung anderer freigeben kann. OPEN hat ein eigenes Projekt auf dieser Plattform. Wenn zu uns deinen Link zu Open Humans zur Verfügung stellst, wird uns dies helfen, dich bei Bedarf anonym kontaktieren zu können und zu weiteren Forschungsergebnissen zu gelangen, indem wir deine Umfrageergebnisse mit deinen weiteren Diabetes-Daten verbinden. Die folgende Schritt-für-Schritt-Anleitung hilft dir dabei, einen Open Humans Account zu erstellen, deine Daten hochzuladen und sie uns zur Verfügung zu stellen."
    override val linkToOpenHumansTextNoData = "Dein Open Humans Account wurde verbunden, es konnten aber keine Datenquellen ausfindig gemacht werden."
    override val linkToOpenHumansTextDataSources = "Dein Open Humans Account wurde verbunden. Die folgenden Datenquellen wurden erkannt:"
    override val androidAPSUploader = "AndroidAPS Uploader"
    override val nightscoutDataTransfer = "Nightscout Data Transfer"
    override val dataSelfie = "Data Selfie"
    override val setup = "Einrichten"
    override val openHumans = "Open Humans"
    override val createOpenHumansAccount = "Erstelle einen Open Humans Account"
    override val signUpOnOpenHumans = "Registriere dich für einen Account auf openhumans.org, falls du noch keinen hast. Du kannst deinen bereits bestehenden Facebook- oder Google-Login wiederverwenden."
    override val uploadYourData = "Lade deine Daten hoch"
    override val chooseDataSource = "Die folgenden Datenquellen stehen zur Verfügung:"
    override val nightscoutDataTransferDescription = "Falls du deine Daten bereits zu Nightscout hochlädst, kannst du dieses einfache Werkzeug dazu nutzen, deine Daten zu Open Humans zu kopieren."
    override val androidAPSUploaderDescription = "Nightscout ist keine Option? Du kannst deine Daten auch direkt von AndroidAPS aus hochladen."
    override val dataSelfieDescription = "Keine der genannten Möglichkeiten passt für dich? Mit dem Data Selfie Werkzeug kannst du alle gewünschten Daten hochladen, z.B. einen Export von Dexcom Clarity oder einer anderen Software deiner Wahl."
    override val noData = "Ich möchte meine Daten nicht hochladen"
    override val noDataDescription = "Das ist auch in Ordnung. Open Humans erlaubt uns trotzdem noch, dich anonym zu kontaktieren. Du kannst jederzeit hierher zurückkehren, falls du deine Meinung änderst."
    override val linkToOpenHumansDescription = "Und hier ist der letzte Schritt, um die Verbindung zu deinem Open Humans Account herzustellen. Du musst diesen Schritt wiederholen, solltest du in der Zukunft eine weitere Datenquelle hinzufügen."
    override val authorize = "Autorisieren"
    override val instructions = "Anleitung"
    override val takeASelfie = "Ein Selfie machen"
    override val signUp = "Registrieren"
    override val accountAlreadyLinked = "Account bereits verknüpft"
    override val accountAlreadyLinkedDescription = "Dieser Open Humans Account ist bereits mit einer anderen Teilnehmer-ID verknüpft."
    override val wrongAccount = "Falscher Account"
    override val pleaseReuseAccount = "Bitte benutze den Account, den du bereits in der Vergangenheit mit deiner Teilnehmer-ID verknüpft hast."
    override val success = "Das war erfolgreich!"
    override val somethingWentWrong = "Etwas ist schiefgelaufen"
    override val couldntConnectToOpenHumans = "Wir konnten keine Verbindung zu deinem Open Humans Account herstellen."
    override val noParticipantIdFound = "Keine Teilnehmer-ID gefunden"
    override val orSignInUsingOpenHumans = "Oder mit Open Humans anmelden"
    override val setupAgain = "Erneut einrichten"
    override val declarationOfConsent = "Einverständniserklärung"
    override val iUnderstandAndAgree = "Ich verstehe und akzeptiere."
    override val consentNotGiven = "Einverständnis nicht erklärt"
    override val consentNotGivenDescription = "Vielen Dank für dein Interesse an dieser Studie, ohne Einverständniserklärung kannst du jedoch nicht teilnehmen."

    override fun loginText(href: String): String =
        """Um fortzufahren, gib bitte deine <b>Teilnehmer-ID</b> ein oder <a href="$href">klicke hier</a>, falls du noch keine hast:"""

    override fun participantId(participantId: String) = "Teilnehmer-ID: <b>$participantId</b>"

    override fun missingDataSources(removeLink: String, tryAgain: String) =
        """Falls die Liste der Datenquellen unvollständig ist, <a target="_blank" href="$removeLink">klicke hier</a>, um die Verknüpfung aufzuheben und <a href="$tryAgain">versuche es erneut</a>. Solltest du eine Datenquelle erst kürzlich eingerichtet haben, kann es eine Weile dauern, bis diese erkannt wird."""

    override fun ifYouDidAddAnyDataSources(removeLink: String, tryAgain: String) =
        """Falls du eine Datenquelle hinzugefügt hast, please <a target="_blank" href="$removeLink">klicke hier</a>, um die Verknüpfung aufzuheben und <a href="$tryAgain">versuche es erneut</a>. Solltest du eine Datenquelle erst kürzlich eingerichtet haben, kann es eine Weile dauern, bis diese erkannt wird."""

    override fun createNewParticipantID(href: String) =
        """Es wurde keine Teilnehmer-ID gefunden, die mit diesem Open Humans Account verknüpft ist. <a href="$href">Erstelle eine neue</a> oder melde dich direkt mit deiner bestehenden Teilnehmer-ID an."""
}*/
