package eu.opendiabetes.gateway

data class GatewaySession(
    val languageCode: String? = null,
    val sessionId: Long? = null,
    val sessionSecret: String? = null
)
