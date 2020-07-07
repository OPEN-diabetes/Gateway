package eu.opendiabetes.gateway.utils

import java.security.SecureRandom

fun generateSecureRandomString(chars: String, length: Int): String {
    val randomString = StringBuilder()
    val secureRandom = SecureRandom()
    repeat(length) {
        randomString.append(chars[secureRandom.nextInt(chars.length)])
    }
    return randomString.toString()
}

fun generateNonMatchingSecureRandomString(chars: String, length: Int, count: Int): List<String> {
    val list = mutableListOf<String>()
    repeat(count) {
        var string: String
        do {
            string = generateSecureRandomString(chars, length)
        } while (list.contains(string))
        list.add(string)
    }
    return list.toList()
}
