package eu.opendiabetes.gateway.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Participants : LongIdTable("participants") {
    val secret = text("secret")
    val enrollmentType = enumeration("enrollment_type", EnrollmentType::class)
    val surveyRecordId = text("survey_record_id").nullable()
    val projectMemberId = text("project_member_id").uniqueIndex().nullable()
    val accessToken = text("access_token").nullable()
    val refreshToken = text("refresh_token").nullable()
    val expiresAt = long("expires_at").nullable()

    class Dao(id: EntityID<Long>) : LongEntity(id) {
        companion object : LongEntityClass<Dao>(Participants)

        var secret by Participants.secret
        var enrollmentType by Participants.enrollmentType
        var surveyRecordId by Participants.surveyRecordId
        var projectMemberId by Participants.projectMemberId
        var accessToken by Participants.accessToken
        var refreshToken by Participants.refreshToken
        var expiresAt by Participants.expiresAt

        val immutable get() = Participant(
            id.value,
            secret,
            enrollmentType,
            surveyRecordId,
            projectMemberId,
            accessToken,
            refreshToken,
            expiresAt
        )
    }
}

data class Participant(
    val id: Long,
    val secret: String,
    val enrollmentType: EnrollmentType,
    val surveyRecordId: String?,
    val projectMemberId: String?,
    val accessToken: String?,
    val refreshToken: String?,
    val expiresAt: Long?
)
