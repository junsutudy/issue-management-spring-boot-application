package app.junsu.userservice.controller

import app.junsu.userservice.model.common.AuthToken
import app.junsu.userservice.model.signin.SignInRequest
import app.junsu.userservice.model.signin.SignInResponse
import app.junsu.userservice.model.signup.SignUpRequest
import app.junsu.userservice.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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

    @DeleteMapping("/signout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun signOut(@AuthToken token: String) {
        userService.signOut(token)
    }
}
