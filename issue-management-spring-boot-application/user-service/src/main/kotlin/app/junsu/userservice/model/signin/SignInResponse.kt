package app.junsu.userservice.model.signin

data class SignInResponse(
    val email: String,
    val username: String,
    val token: String,
)
