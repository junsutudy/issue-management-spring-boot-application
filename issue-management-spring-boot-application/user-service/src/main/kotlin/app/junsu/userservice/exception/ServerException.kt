package app.junsu.userservice.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message) {
    data class UserExistException(
        override val message: String = "User Already Exists",
    ) : ServerException(
        code = 409,
        message = message,
    )
}
