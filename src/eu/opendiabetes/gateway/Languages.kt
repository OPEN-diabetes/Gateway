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
    abstract val iAmNotYetUsingDIYAPSButIntend: String
    abstract val iAmNotUsingDIYAPSAndDontIntend: String
    abstract val isYourChildUsingDIYAPS: String
    abstract val myChildIsUsingDIYAPS: String
    abstract val myChildIsNotYetUsingDIYAPSButIntend: String
    abstract val myChildIsNotUsingDIYAPSAndDontIntend: String
    abstract val iAmNotUsingADIYAPS: String
    abstract val childNotUsingDIYAPS: String
    abstract val notUsingDIYAPS: String
    abstract val yourPartnerHasDiabetes: String
    abstract val childHasAlreadyParticipated: String
    abstract val parentHasAlreadyParticipated: String
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

    abstract fun loginText(href: String): String
    abstract fun participantId(participantId: String): String
    abstract fun missingDataSources(removeLink: String, tryAgain: String): String
    abstract fun ifYouDidAddAnyDataSources(removeLink: String, tryAgain: String): String
    abstract fun createNewParticipantID(href: String): String
}

object English : Language() {
    override val code = "en"
    override val name = "English"
    override val gdpr = "GDPR"
    override val legalNotice = "Legal Notice"
    override val genderNotice: String? = null
    override val hiThere = "Welcome to the OPEN project!"
    override val thanksForHelpingUs = "Thank you for helping us on our research on DIYAPS by participating in this survey!"
    override val submit = "Submit"
    override val unknownId = "This Participant ID does not exist."
    override val newParticipant = "New Participant"
    override val pleaseSelectWhichApplies = "Please select which of the following applies to you:"
    override val iAmAnAdult = "I am an <b>adult</b> with diabetes."
    override val iAmAParent = "I am a <b>parent</b> or <b>caregiver</b> of a child with diabetes."
    override val iAmATeenager = "I am a <b>teenager</b> with diabetes."
    override val iAmAPartner = "My <b>partner</b> has diabetes."
    override val goBack = "Go back"
    override val areYouUsingDIYAPS = "Are you using a DIYAPS?"
    override val iAmUsingDIYAPS = "I <b>am using</b> a DIYAPS."
    override val iAmNotYetUsingDIYAPSButIntend =
        "I am not yet using a DIYAPS, but I <b>intend to do</b> so in the future."
    override val iAmNotUsingDIYAPSAndDontIntend = "I am not using a DIYAPS and I <b>do not</b> intend to do so."
    override val isYourChildUsingDIYAPS = "Is your child using a DIYAPS?"
    override val myChildIsUsingDIYAPS = "My child <b>is using</b> a DIYAPS."
    override val myChildIsNotYetUsingDIYAPSButIntend =
        "My child is not yet using a DIYAPS, but we <b>intend to do</b> so in the future."
    override val myChildIsNotUsingDIYAPSAndDontIntend =
        "My child is not using a DIYAPS and we <b>do not</b> intend to do so."
    override val iAmNotUsingADIYAPS = "I am <b>not</b> (yet) using a DIYAPS."
    override val childNotUsingDIYAPS = "Your child is not using a DIYAPS."
    override val notUsingDIYAPS = "You are not using a DIYAPS."
    override val yourPartnerHasDiabetes = "Your partner has diabetes."
    override val childHasAlreadyParticipated = "Your child has already participated."
    override val parentHasAlreadyParticipated = "Your parent has already participated."
    override val didYourParentAlreadyFillOutTheSurvey = "Did your parent already fill out the survey?"
    override val didYourChildAlreadyFillOutTheSurvey = "Did your child already fill out the survey?"
    override val iAmNewToThisSurvey = "I am new to this survey."
    override val weAreNewToThisSurvey = "We are new to this survey."
    override val myParentHasAlreadyFilledOutTheSurvey = "My parent has already filled out the survey."
    override val myChildHasAlreadyFilledOutTheSurvey = "My child has already filled out the survey."
    override val notWithinAudience =
        "Thank you for your interest in this study. Unfortunately you are not within our target audience."
    override val askChildForParticipationLink = "Please ask your child to send you a participation link."
    override val askParentForParticipationLink = "Please ask your parent/caregiver to send you a participation link."
    override val askPartnerForParticipationLink = "Please ask your partner to send you a participation link."
    override val askPatientForParticipationLink = "Please ask your patient to send you a participation link."
    override val yourParticipantIdIs = "Your Participant ID is:"
    override val pleaseNoteItDownToAvoidLosingIt = "It is meant to be secret. To avoid losing it, please note it down in a place only accessible to you."
    override val proceed = "Proceed"
    override val yourTODOs = "Your TO-DOs"
    override val askParent = "Ask your parent/caregiver to also fill out the survey"
    override val askChild = "Ask your child to also fill out the survey"
    override val askPartner = "Ask your partner to also fill out the survey"
    override val sendParent = "Please send the following participation link to your parent:"
    override val sendChild = "Please send the following participation link to your child:"
    override val sendPartner = "Please send the following participation link to your partner:"
    override val fillOutSurvey = "Fill out survey"
    override val answerAFewQuestions = "Please answer a few questions. This will take no longer than 20 to 30 minutes."
    override val goToSurvey = "Go to survey"
    override val signOut = "Sign out"
    override val linkToOpenHumans = "Link to Open Humans"
    override val linkToOpenHumansTextSetup = "This helps us to contact you anonymously if needed and optionally draw further conclusions by linking your survey results with other diabetes data. The following step-by-step guide will help you create an Open Humans account, upload your data and make it available to us."
    override val linkToOpenHumansTextNoData = "Your Open Humans account has been connected, however, no data sources have been detected."
    override val linkToOpenHumansTextDataSources = "Your Open Humans account has been connected. The following data sources were detected:"
    override val androidAPSUploader = "AndroidAPS Uploader"
    override val nightscoutDataTransfer = "Nightscout Data Transfer"
    override val dataSelfie = "Data Selfie"
    override val setup = "Setup"
    override val openHumans = "Open Humans"
    override val createOpenHumansAccount = "Create an Open Humans account"
    override val signUpOnOpenHumans = "Sign up for an account on openhumans.org if you do not have one yet. You can re-use your existing Facebook or Google login."
    override val uploadYourData = "Upload your data"
    override val chooseDataSource = "The following data sources are available:"
    override val nightscoutDataTransferDescription = "If you are already uploading your data to Nightscout, use this simple tool to copy your data over to Open Humans."
    override val androidAPSUploaderDescription = "Nightscout is not an option? You can directly upload your data from within AndroidAPS."
    override val dataSelfieDescription = "None of the above work out for you? The Data Selfie tool allows you to upload any data you want, e.g. an export from Dexcom Clarity or another software of your choice."
    override val noData = "I do not want to upload my data"
    override val noDataDescription = "This is also fine. Open Humans still allows us to contact you anonymously. You can come back to this site at any time if you have changed your mind."
    override val linkToOpenHumansDescription = "One last step to finally establish the link to your Open Humans account. You have to redo this step if you decide to add a data source in the future."
    override val authorize = "Authorize"
    override val instructions = "Instructions"
    override val takeASelfie = "Take a Selfie"
    override val signUp = "Sign up"
    override val accountAlreadyLinked = "Account already linked"
    override val accountAlreadyLinkedDescription = "This Open Humans account is already linked to another Participant ID."
    override val wrongAccount = "Wrong account"
    override val pleaseReuseAccount = "Please reuse the account that you have already used in the past to link to your Participant ID."
    override val success = "Success!"
    override val somethingWentWrong = "Something went wrong"
    override val couldntConnectToOpenHumans = "We could not connect to your Open Humans account."
    override val noParticipantIdFound = "No Participant ID found"
    override val orSignInUsingOpenHumans = "Or sign in using Open Humans"
    override val setupAgain = "Setup again"
    override val declarationOfConsent = "Declaration of Consent"
    override val iUnderstandAndAgree = "I understand and agree."
    override val consentNotGiven = "Consent not given"
    override val consentNotGivenDescription = "Thank you for your interest in this study, however, you cannot proceed without giving your consent."

    override fun loginText(href: String) =
        """To proceed please type in your <b>Participant ID</b> or <a href="$href">click here</a> if you do not have one yet:"""

    override fun participantId(participantId: String) = "Participant ID: <b>$participantId</b>"

    override fun missingDataSources(removeLink: String, tryAgain: String) =
        """If the list of data sources appears to be incomplete, please <a target="_blank" href="$removeLink">click here</a> to remove the link and <a href="$tryAgain">try again</a>. If you have recently setup a new data source, it might take a while to be detected."""

    override fun ifYouDidAddAnyDataSources(removeLink: String, tryAgain: String) =
        """If you did add any data sources, please <a target="_blank" href="$removeLink">click here</a> to remove the link and <a href="$tryAgain">try again</a>. If you have recently setup a new data source, it might take a while to be detected."""

    override fun createNewParticipantID(href: String) =
        """No Participant ID affiliated to this Open Humans account has been found. Either <a href="$href">create a new one</a> or try to sign in directly using your existing Participant ID."""
}


object German : Language() {
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
    override val iAmAPartner = "Mein <b>Partner</b> hat Diabetes."
    override val goBack = "Zurück"
    override val areYouUsingDIYAPS = "Benutzt du ein DIYAPS?"
    override val iAmUsingDIYAPS = "Ich <b>benutze</b> ein DIYAPS."
    override val iAmNotYetUsingDIYAPSButIntend = "Ich benutze noch kein DIYAPS, <b>beabsichtige</b> dies aber in der Zukunft zu tun."
    override val iAmNotUsingDIYAPSAndDontIntend = "Ich benutze kein DIYAPS und habe auch <b>nicht vor</b> dies zu tun."
    override val isYourChildUsingDIYAPS = "Benutzt dein Kind ein DIYAPS?"
    override val myChildIsUsingDIYAPS = "Mein Kind <b>benutzt</b> ein DIYAPS."
    override val myChildIsNotYetUsingDIYAPSButIntend = "Mein Kind benutzt noch kein DIYAPS, wir <b>beabsichtigen</b> dies aber in der Zukunft zu tun."
    override val myChildIsNotUsingDIYAPSAndDontIntend = "Mein Kind benutzt <b>kein DIYAPS</b> und wir haben auch nicht vor dies zu tun."
    override val iAmNotUsingADIYAPS = "Ich benutze (noch) <b>kein DIYAPS</b>."
    override val childNotUsingDIYAPS = "Dein Kind benutzt kein DIYAPS."
    override val notUsingDIYAPS = "Du benutzt kein DIYAPS."
    override val yourPartnerHasDiabetes = "Dein Partner hat Diabetes."
    override val childHasAlreadyParticipated = "Dein Kind hat bereits teilgenommen."
    override val parentHasAlreadyParticipated = "Ein Elternteil hat bereits teilgenommen."
    override val didYourParentAlreadyFillOutTheSurvey = "Hat ein Elternteil die Umfrage bereits ausgefüllt?"
    override val didYourChildAlreadyFillOutTheSurvey = "Hat dein Kind die Umfrage bereits ausgefüllt?"
    override val iAmNewToThisSurvey = "Ich bin neu bei dieser Umfrage."
    override val weAreNewToThisSurvey = "Wir sind neu bei dieser Umfrage."
    override val myParentHasAlreadyFilledOutTheSurvey = "Ein Elternteil hat die Umfrage bereits ausgefüllt."
    override val myChildHasAlreadyFilledOutTheSurvey = "Mein Kind hat die Umfrage bereits ausgefüllt."
    override val notWithinAudience = "Vielen Dank für dein Interesse an dieser Studie. Leider liegst du nicht innerhalb unserer Zielgruppe."
    override val askChildForParticipationLink = "Bitte frage dein Kind nach einem Teilnahme-Link."
    override val askParentForParticipationLink = "Bitte frage dein Elternteil nach einem Teilnahme-Link."
    override val askPartnerForParticipationLink = "Bitte frage deinen Partner nach einem Teilnahme-Link."
    override val askPatientForParticipationLink = "Bitte frage deinen Patienten nach einem Teilnahme-Link."
    override val yourParticipantIdIs = "Deine Teilnehmer-ID lautet:"
    override val pleaseNoteItDownToAvoidLosingIt = "Sie soll nur dir bekannt sein. Bitte schreibe sie dir auf, um sie nicht zu verlieren."
    override val proceed = "Fortfahren"
    override val yourTODOs = "Deine TODOs"
    override val askParent = "Bitte ein Elternteil, die Umfrage ebenfalls auszufüllen"
    override val askChild = "Bitte dein Kind, die Umfrage ebenfalls auszufüllen"
    override val askPartner = "Bitte deinen Partner, die Umfrage ebenfalls auszufüllen"
    override val sendParent = "Sende dazu den folgendenen Teilnahme-Link an dein Elternteil:"
    override val sendChild = "Sende dazu den folgendenen Teilnahme-Link an dein Kind:"
    override val sendPartner = "Sende dazu den folgendenen Teilnahme-Link an deinen Partner:"
    override val fillOutSurvey = "Umfrage ausfüllen"
    override val answerAFewQuestions = "Beantworte uns ein paar Fragen. Dies wird nicht mehr als 20 bis 30 Minuten in Anspruch nehmen."
    override val goToSurvey = "Zur Umfrage gehen"
    override val signOut = "Abmelden"
    override val linkToOpenHumans = "Mit Open Humans verknüpfen"
    override val linkToOpenHumansTextSetup = "Dies hilft uns, dich anonym kontaktieren zu können und optional zu weiteren Forschungsergebnissen zu gelangen, indem wir deine Umfrageergebnisse mit weiteren Diabetes-Daten verbinden. Die folgende Schritt-für-Schritt-Anleitung hilft dir dabei, einen Open Humans Account zu erstellen, deine Daten hochzuladen und sie uns zur Verfügung zu stellen."
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
}
