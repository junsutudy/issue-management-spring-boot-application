package app.junsu.userservice.controller

import app.junsu.userservice.model.signin.SignInRequest
import app.junsu.userservice.model.signin.SignInResponse
import app.junsu.userservice.model.signup.SignUpRequest
import app.junsu.userservice.service.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping
    suspend fun signUp(
        @RequestBody request: SignUpRequest,
    ) {
        userService.signUp(request)
    }

    @PostMapping("/signin")
    suspend fun signIn(
        @RequestBody request: SignInRequest,
    ): SignInResponse {
        return userService.signIn(request)
    }
}
