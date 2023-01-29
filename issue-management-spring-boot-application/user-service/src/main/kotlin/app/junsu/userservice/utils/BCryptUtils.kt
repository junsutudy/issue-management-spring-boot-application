package app.junsu.userservice.utils

import at.favre.lib.crypto.bcrypt.BCrypt

object BCryptUtils {

    fun hash(
        password: String,
    ): String {
        return BCrypt.withDefaults().hashToString(
            12,
            password.toCharArray(),
        )
    }

    fun verify(
        password: String,
        hashedPassword: String,
    ): Boolean {
        return BCrypt.verifyer().verify(
            password.toCharArray(),
            hashedPassword,
        ).verified
    }
}

fun String.hash(): String {
    return BCryptUtils.hash(this)
}
