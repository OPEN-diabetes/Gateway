package eu.opendiabetes.gateway.utils

import eu.opendiabetes.gateway.database.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.DaoEntityID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

private const val PARTICIPANT_ID_SECRET_CHARS = "123456789ABCDEFGHJKMNPRSTUXYZ"
private const val PARTICIPANT_ID_SECRET_LENGTH = 9
private const val PARTICIPATION_LINK_SECRET_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
private const val PARTICIPATION_LINK_SECRET_LENGTH = 10
private const val SESSION_SECRET_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
private const val SESSION_SECRET_LENGTH = 32

suspend fun Database.getSession(id: Long, secret: String) = newSuspendedTransaction(Dispatchers.IO, this) {
    Sessions.Dao.find {
        Sessions.id.eq(id) and Sessions.secret.eq(secret)
    }.firstOrNull()?.immutable
}

suspend fun Database.getParticipant(id: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.Dao.findById(id)?.immutable
}

suspend fun Database.getAllParticipantsWithOAuth() = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.Dao.find { Participants.accessToken.isNotNull() }.map { it.immutable }
}

suspend fun Database.createSession(participantId: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    Sessions.Dao.new {
        this.participantId = DaoEntityID(participantId, Participants)
        this.secret = generateSecureRandomString(SESSION_SECRET_CHARS, SESSION_SECRET_LENGTH)
    }.immutable
}

suspend fun Database.deleteSession(id: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    Sessions.deleteWhere { Sessions.id eq id }
}

suspend fun Database.setSurveyRecordIdForParticipant(id: Long, recordId: String) = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.update({ Participants.id eq id }) {
        it[Participants.surveyRecordId] = recordId
    }
}

suspend fun Database.setFollowupSurveyRecordIdForParticipant(id: Long, recordId: String) = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.update({ Participants.id eq id }) {
        it[Participants.followupSurveyRecordId] = recordId
    }
}

suspend fun Database.getParticipationLink(id: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    ParticipationLinks.Dao.findById(id)?.immutable
}

suspend fun Database.setOpenHumansTokensForParticipant(id: Long, accessToken: String, refreshToken: String, expiresAt: Long, projectMemberId: String? = null) = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.update({ Participants.id eq id }) {
        it[Participants.accessToken] = accessToken
        it[Participants.refreshToken] = refreshToken
        it[Participants.expiresAt] = expiresAt
        if (projectMemberId != null) it[Participants.projectMemberId] = projectMemberId
    }
}

suspend fun Database.setSurveyRecordIdForParticipationLink(id: Long, surveyLink: String) = newSuspendedTransaction(Dispatchers.IO, this) {
    ParticipationLinks.update({ ParticipationLinks.id eq id }) {
        it[ParticipationLinks.surveyRecordId] = surveyLink
    }
}

suspend fun Database.createHcpLinkForParticipantIfNeeded(id: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    var hcpLink = HcpLinks.Dao.find {
        HcpLinks.participant eq id
    }.firstOrNull()
    if (hcpLink == null) {
        hcpLink = HcpLinks.Dao.new {
            this.participantId = EntityID(id, Participants)
            this.secret = generateSecureRandomString(PARTICIPATION_LINK_SECRET_CHARS, PARTICIPATION_LINK_SECRET_LENGTH)
        }
    }
    hcpLink.immutable
}

suspend fun Database.getHcpLinkForParticipant(id: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    HcpLinks.Dao.find {
        HcpLinks.participant eq id
    }.firstOrNull()?.immutable
}

suspend fun Database.setSurveyRecordIdForHcpLink(id: Long, surveyRecordId: String) = newSuspendedTransaction(Dispatchers.IO, this) {
    HcpLinks.update({ HcpLinks.id eq id }) {
        it[eu.opendiabetes.gateway.database.HcpLinks.surveyRecordId] = surveyRecordId
    }
}

suspend fun Database.getSurveyLinksForParticipant(id: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    ParticipationLinks.Dao.find {
        ParticipationLinks.participant eq id
    }.map { it.immutable }
}

suspend fun Database.getParticipantByProjectMemberId(projectMemberId: String) = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.Dao.find { Participants.projectMemberId eq projectMemberId }.firstOrNull()?.immutable
}

suspend fun Database.createParticipantId(enrollmentType: EnrollmentType) = newSuspendedTransaction(Dispatchers.IO, this) {
    val participant = Participants.Dao.new {
        this.secret = generateSecureRandomString(PARTICIPANT_ID_SECRET_CHARS, PARTICIPANT_ID_SECRET_LENGTH)
        this.enrollmentType = enrollmentType
        this.informationSheetShown = true
    }
    participant.immutable
}

suspend fun Database.setInformationSheetShown(participantId: Long) = newSuspendedTransaction(Dispatchers.IO, this) {
    Participants.update({ Participants.id eq participantId }) {
        it[informationSheetShown] = true
    }
}
