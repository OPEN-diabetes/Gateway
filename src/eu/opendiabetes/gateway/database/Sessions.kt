package eu.opendiabetes.gateway.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Sessions : LongIdTable("sessions") {
    val participantId = reference(
        "participant",
        Participants,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val secret = text("secret")

    class Dao(id: EntityID<Long>) : LongEntity(id) {
        companion object : LongEntityClass<Dao>(Sessions)

        var participantId by Sessions.participantId
        var secret by Sessions.secret

        val immutable get() = Session(
            id.value,
            participantId.value,
            secret
        )
    }
}

data class Session(
    val id: Long,
    val participantId: Long,
    val secret: String
)
