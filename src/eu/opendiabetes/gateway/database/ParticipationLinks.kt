package eu.opendiabetes.gateway.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ParticipationLinks : LongIdTable("participation_links") {
    val participant = reference(
        "participant",
        Participants,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val enrollmentType = enumeration("enrollment_type", EnrollmentType::class)
    val secret = text("secret")
    val surveyRecordId = text("survey_record_id").nullable()

    class Dao(id: EntityID<Long>) : LongEntity(id) {
        companion object : LongEntityClass<Dao>(ParticipationLinks)

        var participantId by ParticipationLinks.participant
        var enrollmentType by ParticipationLinks.enrollmentType
        var secret by ParticipationLinks.secret
        var surveyRecordId by ParticipationLinks.surveyRecordId

        val immutable get() = ParticipationLink(
            id.value,
            participantId.value,
            enrollmentType,
            secret,
            surveyRecordId
        )
    }
}

data class ParticipationLink(
    val id: Long,
    val participantId: Long,
    val enrollmentType: EnrollmentType,
    val secret: String,
    val surveyRecordId: String?
)
