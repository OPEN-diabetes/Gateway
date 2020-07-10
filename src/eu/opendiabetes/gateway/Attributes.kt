package eu.opendiabetes.gateway

import eu.opendiabetes.gateway.database.Participant
import eu.opendiabetes.gateway.database.Session
import eu.opendiabetes.gateway.utils.OpenHumansAPI
import io.ktor.util.AttributeKey
import org.jetbrains.exposed.sql.Database

object GatewayAttributes {
    val database = AttributeKey<Database>("Database")
    val openHumansAPI = AttributeKey<OpenHumansAPI>("OpenHumansAPI")
    val language = AttributeKey<Language>("Language")
    val session = AttributeKey<Session>("Session")
    val participant = AttributeKey<Participant>("Participant")
}

