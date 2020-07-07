package eu.opendiabetes.gateway

import eu.opendiabetes.gateway.database.Participant
import eu.opendiabetes.gateway.database.Session
import io.ktor.util.AttributeKey
import org.jetbrains.exposed.sql.Database

object GatewayAttributes {
    val database = AttributeKey<Database>("Database")
    val language = AttributeKey<Language>("Language")
    val session = AttributeKey<Session>("Session")
    val participant = AttributeKey<Participant>("Participant")
}

