package eu.opendiabetes.gateway.modules

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import eu.opendiabetes.gateway.database.HcpLinks
import eu.opendiabetes.gateway.database.Participants
import eu.opendiabetes.gateway.database.ParticipationLinks
import eu.opendiabetes.gateway.database.Sessions
import eu.opendiabetes.gateway.utils.database
import eu.opendiabetes.gateway.utils.getStringProperty
import io.ktor.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.databaseModule() {
    val config = HikariConfig(getStringProperty("gateway.database.hikariConfig"))
    val dataSource = HikariDataSource(config)
    database = Database.connect(dataSource)
    transaction(database) {
        SchemaUtils.create(Participants, Sessions, ParticipationLinks, HcpLinks)
    }
}
