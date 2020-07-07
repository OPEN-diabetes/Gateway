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
    abstract val iAmAHCP: String
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
    abstract val youAreAHCP: String
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
    abstract val askHCP: String
    abstract val askPartner: String
    abstract val sendParent: String
    abstract val sendChild: String
    abstract val sendHCP: String
    abstract val sendPartner: String
    abstract val fillOutSurvey: String
    abstract val answerAFewQuestions: String
    abstract val goToSurvey: String
    abstract val signOut: String

    abstract fun loginText(href: String): String
    abstract fun participantId(participantId: String): String
}

object English : Language() {
    override val code = "en"
    override val name = "English"
    override val gdpr = "GDPR"
    override val legalNotice = "Legal Notice"
    override val genderNotice: String? = null
    override val hiThere = "Hi there!"
    override val thanksForHelpingUs = "Thanks for helping us by participating in this survey!"
    override val submit = "Submit"
    override val unknownId = "This Participant ID does not exist."
    override val newParticipant = "New Participant"
    override val pleaseSelectWhichApplies = "Please select which of the following applies to you:"
    override val iAmAnAdult = "I am an <b>adult</b> with diabetes."
    override val iAmAParent = "I am a <b>parent</b> of a child with diabetes."
    override val iAmATeenager = "I am a <b>teenager</b> with diabetes."
    override val iAmAPartner = "My <b>partner</b> has diabetes."
    override val iAmAHCP = "I am a <b>health care professional</b>."
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
        "My child not using a DIYAPS and we <b>do not</b> intend to do so."
    override val iAmNotUsingADIYAPS = "I am <b>not</b> (yet) using a DIYAPS."
    override val childNotUsingDIYAPS = "Your child is not using a DIYAPS."
    override val notUsingDIYAPS = "You are not using a DIYAPS."
    override val yourPartnerHasDiabetes = "Your partner has diabetes."
    override val youAreAHCP = "You are health care professional."
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
    override val askParentForParticipationLink = "Please ask your parent to send you a participation link."
    override val askPartnerForParticipationLink = "Please ask your loved one to send you a participation link."
    override val askPatientForParticipationLink = "Please ask your patient to send you a participation link."
    override val yourParticipantIdIs = "Your Participant ID is:"
    override val pleaseNoteItDownToAvoidLosingIt = "It is meant to be secret. Please note it down to avoid losing it."
    override val proceed = "Proceed"
    override val yourTODOs = "Your Todos"
    override val askParent = "Ask your parent to also fill out the survey"
    override val askChild = "Ask your child to also fill out the survey"
    override val askHCP = "Ask your health care professional to also fill out the survey"
    override val askPartner = "Ask your partner to also fill out the survey"
    override val sendParent = "Please send the following participation link to your parent:"
    override val sendChild = "Please send the following participation link to your child:"
    override val sendHCP = "Please send the following participation link to your health care professional:"
    override val sendPartner = "Please send the following participation link to your partner:"
    override val fillOutSurvey = "Fill out survey"
    override val answerAFewQuestions = "Answer a few questions. This will take no longer than 20 to 30 minutes."
    override val goToSurvey = "Go to survey"
    override val signOut = "Sign out"

    override fun loginText(href: String) =
        """To proceed please enter your <b>Participant ID</b> or <a href="$href">click here</a> if you do not have one yet:"""

    override fun participantId(participantId: String) = "Participant ID: <b>$participantId</b>"
}


object German : Language() {
    override val code = "de"
    override val name = "Deutsch"
    override val gdpr = "DSGVO"
    override val legalNotice = "Impressum"
    override val genderNotice = "Der Einfachheit halber wird immer nur die männliche Form genannt. Es sind aber immer alle Geschlechter gemeint!"
    override val hiThere = "Hallo!"
    override val thanksForHelpingUs = "Danke, dass du uns hilfst, indem du an dieser Umfrage teilnimmst!"
    override val submit = "Senden"
    override val unknownId = "Diese Teilnehmer-ID existiert nicht."
    override val newParticipant = "Neuer Teilnehmer"
    override val pleaseSelectWhichApplies = "Bitte wähle, welches der Folgenden auf dich zutrifft:"
    override val iAmAnAdult = "Ich bin ein <b>Erwachsener</b> mit Diabetes."
    override val iAmAParent = "Ich bin <b>Elternteil</b> eines Kindes mit Diabetes."
    override val iAmATeenager = "Ich bin ein <b>Jugendlicher</b> mit Diabetes."
    override val iAmAPartner = "Mein <b>Partner</b> hat Diabetes."
    override val iAmAHCP = "Ich bin <b>medizinisches Fachpersonal</b>."
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
    override val youAreAHCP = "Du bist medizinisches Fachpersonal."
    override val childHasAlreadyParticipated = "Dein Kind hat bereits teilgenommen."
    override val parentHasAlreadyParticipated = "Ein Elternteil hat bereits teilgenommen."
    override val didYourParentAlreadyFillOutTheSurvey = "Hat ein Elternteil die Umfrage bereits ausgefüllt?"
    override val didYourChildAlreadyFillOutTheSurvey = "Hat dein Kind die Umfrage bereits ausgefüllt?"
    override val iAmNewToThisSurvey = "Ich bin neu bei dieser Umfrage."
    override val weAreNewToThisSurvey = "Wir sind neu bei dieser Umfrage."
    override val myParentHasAlreadyFilledOutTheSurvey = "Ein Elternteil hat die Umfrage bereits ausgefüllt."
    override val myChildHasAlreadyFilledOutTheSurvey = "Mein Kind hat die Umfrage bereits ausgefüllt."
    override val notWithinAudience = "Vielen Dank für dein Interesse an dieser Studie. Leider liegst du nicht innerhalb unserer Zielgruppe."
    override val askChildForParticipationLink = "Bitte Frage dein Kind nach einem Teilnahme-Link."
    override val askParentForParticipationLink = "Bitte Frage dein Elternteil nach einem Teilnahme-Link."
    override val askPartnerForParticipationLink = "Bitte Frage deinen Partner nach einem Teilnahme-Link."
    override val askPatientForParticipationLink = "Bitte Frage deinen Patienten nach einem Teilnahme-Link."
    override val yourParticipantIdIs = "Deine Teilnehmer-ID lautet:"
    override val pleaseNoteItDownToAvoidLosingIt = "Sie soll nur dir bekannt sein. Bitte schreibe sie dir auf, um sie nicht zu verlieren."
    override val proceed = "Fortfahren"
    override val yourTODOs = "Deine TODOs"
    override val askParent = "Bitte ein Elternteil die Umfrage ebenfalls auszufüllen"
    override val askChild = "Bitte dein Kind die Umfrage ebenfalls auszufüllen"
    override val askHCP = "Bitte das dich betreuende medizinische Fachpersonal die Umfrage ebenfalls auszufüllen"
    override val askPartner = "Bitte deinen Partner die Umfrage ebenfalls auszufüllen"
    override val sendParent = "Sende dazu den folgendenen Teilnahme-Link an dein Elternteil:"
    override val sendChild = "Sende dazu den folgendenen Teilnahme-Link an dein Kind:"
    override val sendHCP = "Sende dazu den folgendenen Teilnahme-Link an das dich betreuende medizinische Fachpersonal:"
    override val sendPartner = "Sende dazu den folgendenen Teilnahme-Link an deinen Partner:"
    override val fillOutSurvey = "Umfrage ausfüllen"
    override val answerAFewQuestions = "Beantworte uns ein Fragen. Dies wird nicht länger als 20 bis 30 Minuten in Anspruch nehmen."
    override val goToSurvey = "Zur Umfrage gehen"
    override val signOut = "Abmelden"

    override fun loginText(href: String): String =
        """Um fortzufahren, gib bitte deine <b>Teilnehmer-ID</b> ein oder <a href="$href">klicke hier</a>, falls du noch keine hast:"""

    override fun participantId(participantId: String) = "Teilnehmer-ID: <b>$participantId</b>"
}

