package eu.opendiabetes.gateway

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import eu.opendiabetes.gateway.modules.todosModule
import eu.opendiabetes.gateway.utils.*
import io.ktor.client.features.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.jetbrains.exposed.sql.Database

fun main() = runBlocking {
    val config = HikariConfig(System.getenv("HIKARI_CONFIG"))
    val dataSource = HikariDataSource(config)
    val database = Database.connect(dataSource)
    val openHumans = OpenHumansAPI(System.getenv("CLIENT_ID"), System.getenv("CLIENT_SECRET"))
    val participants = database.getAllParticipantsWithOAuth()
    println("Checking OAuth2 tokens for ${ participants.size } participants")
    var successfulChecks = 0;
    participants.forEachIndexed { index, participant ->
        print("Checking ${ participant.id } (${ index + 1 }/${ participants.size }) - ")
        try {
            val accessToken = if (participant.expiresAt!! <= System.currentTimeMillis()) {
                val (accessToken, refreshToken, expiresAt) = openHumans.refreshAccessToken(participant.refreshToken!!)
                database.setOpenHumansTokensForParticipant(
                    participant.id,
                    accessToken,
                    refreshToken,
                    expiresAt
                )
                accessToken
            } else {
                participant.accessToken!!
            }
            openHumans.getProjectMemberInfo(accessToken)
            successfulChecks++
            println("SUCCESS")
        } catch (e: ClientRequestException) {
            println("FAILURE")
        }
    }
    println("$successfulChecks/${ participants.size } succeeded")
}
