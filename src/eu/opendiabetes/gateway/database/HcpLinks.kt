package eu.opendiabetes.gateway.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object HcpLinks : LongIdTable("hcp_links") {
    val participant = reference(
        "participant",
        Participants,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val secret = text("secret")
    val surveyRecordId = text("survey_record_id").nullable()

    init {
        index(true, participant)
    }

    class Dao(id: EntityID<Long>) : LongEntity(id) {
        companion object : LongEntityClass<Dao>(HcpLinks)

        var participantId by HcpLinks.participant
        var secret by HcpLinks.secret
        var surveyRecordId by HcpLinks.surveyRecordId

        val immutable
            get() = HcpLink(
                id.value,
                participantId.value,
                secret,
                surveyRecordId
            )
    }
}

data class HcpLink(
    val id: Long,
    val participantId: Long,
    val secret: String,
    val surveyRecordId: String?
)
