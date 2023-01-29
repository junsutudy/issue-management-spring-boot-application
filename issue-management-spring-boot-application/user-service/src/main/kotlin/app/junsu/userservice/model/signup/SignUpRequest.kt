package app.junsu.userservice.model.signup

data class SignUpRequest(
    val email: String,
    val password: String,
    val username: String,
)
