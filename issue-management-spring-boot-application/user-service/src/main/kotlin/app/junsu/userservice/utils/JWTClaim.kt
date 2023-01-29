package app.junsu.userservice.utils

data class JWTClaim(
    val userId: Long,
    val email: String,
    val profileUrl: String?,
    val username: String,
)
