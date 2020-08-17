package eu.opendiabetes.gateway.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.content
import java.util.concurrent.TimeUnit

class OpenHumansAPI(
    clientId: String,
    clientSecret: String
) : AutoCloseable {
    private val httpClient = HttpClient(Apache) {
        install(Auth) {
            basic {
                sendWithoutRequest = true
                username = clientId
                password = clientSecret
            }
        }
    }
    private val json = Json(JsonConfiguration.Stable)

    suspend fun getProjectMemberInfo(accessToken: String): ProjectMemberInfo {
        val response = httpClient.get<String>(PROJECT_MEMBER_ENDPOINT) {
            parameter("access_token", accessToken)
        }
        val responseJson = json.parseJson(response).jsonObject
        return ProjectMemberInfo(
            projectMemberId = responseJson["project_member_id"]!!.content,
            sourcesOfData = responseJson["data"]!!.jsonArray.map { it.jsonObject["source"]!!.content }.toSet()
        )
    }

    suspend fun refreshAccessToken(refreshToken: String): FreshTokens {
        val response = httpClient.submitForm<String>(ACCESS_TOKEN_URL, Parameters.build {
            append("grant_type", "refresh_token")
            append("refresh_token", refreshToken)
        })
        val responseJson = json.parseJson(response).jsonObject
        return FreshTokens(
            responseJson["access_token"]!!.content,
            responseJson["refresh_token"]!!.content,
            System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(responseJson["expires_in"]!!.primitive.long)
        )
    }

    suspend fun removeMember(accessToken: String) {
        httpClient.post<String>(REMOVE_MEMBER_URL) {
            parameter("access_token", accessToken)
        }
    }

    override fun close() {
        httpClient.close()
    }

    companion object {
        const val API_BASE = "https://www.openhumans.org"
        const val PROJECT_MEMBER_ENDPOINT = "$API_BASE/api/direct-sharing/project/exchange-member/"
        const val AUTHORIZE_URL = "$API_BASE/direct-sharing/projects/oauth2/authorize/"
        const val REMOVE_MEMBER_URL = "$API_BASE/api/direct-sharing/project/remove-members/"
        const val ACCESS_TOKEN_URL = "$API_BASE/oauth2/token/"
        const val NIGHTSCOUT_ID = "direct-sharing-31"
        const val SELFIES_ID = "direct-sharing-133"
        const val ANDROIDAPS_ID = "direct-sharing-396"
    }
}

data class ProjectMemberInfo(
    val projectMemberId: String,
    val sourcesOfData: Set<String>
)

data class FreshTokens(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Long
)
