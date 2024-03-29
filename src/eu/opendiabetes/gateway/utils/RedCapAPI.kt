package eu.opendiabetes.gateway.utils

import eu.opendiabetes.gateway.database.EnrollmentType
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.forms.submitForm
import io.ktor.http.Parameters
import kotlinx.serialization.json.*

class RedCapAPI(
    private val apiUrl: String,
    private val token: String
) : AutoCloseable {
    private val httpClient = HttpClient(Apache)
    private val json = Json(JsonConfiguration.Stable)

    suspend fun createRecord(participantId: Long, enrollmentType: Int): String {
        val data = json.stringify(JsonElementSerializer,
            jsonArray {
                +json {
                    "record_id" to 1
                    "participant_id" to participantId
                    "enrollment_type" to enrollmentType
                    "gateway_complete" to 2
                }
            })
        val params = Parameters.build {
            append("token", token)
            append("content", "record")
            append("format", "json")
            append("type", "flat")
            append("forceAutoNumber", "true")
            append("data", data)
            append("returnContent", "auto_ids")
            append("returnFormat", "json")
        }
        val response = httpClient.submitForm<String>(apiUrl, params)
        val autoIds = json.parse(JsonElementSerializer, response)
        return autoIds.jsonArray.first().content.split(",").first()
    }

    suspend fun exportSurveyQueueLink(recordId: String): String {
        val params = Parameters.build {
            append("token", token)
            append("content", "surveyQueueLink")
            append("format", "json")
            append("record", recordId)
            append("returnFormat", "json")
        }
        return httpClient.submitForm(apiUrl, params)
    }

    suspend fun hasCompletedSurvey(recordId: String): Boolean {
        val params = Parameters.build {
            append("token", token)
            append("content", "record")
            append("format", "json")
            append("type", "eav")
            append("records", recordId)
            append("fields", "thank_you_a_complete,thank_you_anot_complete,thank_you_parent_complete,thank_you_parentnot_complete,healthcare_professionals_complete")
            append("returnFormat", "json")
        }
        val response = httpClient.submitForm<String>(apiUrl, params)
        val records = json.parse(JsonArraySerializer, response)
        return records.any { it.jsonObject.getPrimitive("value").content == "2" }
    }

    override fun close() {
        httpClient.close()
    }
}
