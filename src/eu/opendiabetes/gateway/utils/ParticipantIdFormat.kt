package eu.opendiabetes.gateway.utils

fun formatParticipantId(id: Long, secret: String): String {
    val formattedSecret = secret.replace("-", "").windowed(3, 3, true).joinToString("-")
    return "$id-$formattedSecret"
}
