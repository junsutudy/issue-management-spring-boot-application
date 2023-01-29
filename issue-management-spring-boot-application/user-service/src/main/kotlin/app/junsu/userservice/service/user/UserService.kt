package app.junsu.userservice.service.user

import app.junsu.userservice.config.JWTProperties
import app.junsu.userservice.domain.entity.user.User
import app.junsu.userservice.domain.repository.user.UserRepository
import app.junsu.userservice.exception.ServerException.*
import app.junsu.userservice.model.signin.SignInRequest
import app.junsu.userservice.model.signin.SignInResponse
import app.junsu.userservice.model.signup.SignUpRequest
import app.junsu.userservice.service.CoroutineCacheManager
import app.junsu.userservice.utils.BCryptUtils
import app.junsu.userservice.utils.JWTClaim
import app.junsu.userservice.utils.JWTUtils
import app.junsu.userservice.utils.hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class UserService(
    @Autowired val userRepository: UserRepository,
    @Autowired val jwtProperties: JWTProperties,
    @Autowired val cacheManager: CoroutineCacheManager<User>,
) {

    companion object {

        private val CACHE_TTL = Duration.ofMinutes(1L)
    }

    suspend fun signUp(
        signUpRequest: SignUpRequest,
    ) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserExistException()
            }

            val user = User(
                email = email,
                password = password.hash(),
                username = username,
            )

            userRepository.save(user)
        }
    }

    suspend fun signIn(
        signInRequest: SignInRequest,
    ): SignInResponse {
        return with(userRepository.findByEmail(signInRequest.email) ?: throw UserNotFoundException()) {
            val verified = BCryptUtils.verify(signInRequest.password, password)
            if (!verified) {
                throw PasswordMismatchException()
            }

            val jwtClaim = JWTClaim(
                userId = id!!,
                email = email,
                profileUrl = profileUrl,
                username = username,
            )

            val token = JWTUtils.createToken(
                claim = jwtClaim, properties = jwtProperties
            )

            cacheManager.awaitPut(
                key = token,
                value = this,
                ttl = CACHE_TTL,
            )

            SignInResponse(
                email = email,
                username = username,
                token = token,
            )
        }
    }
}
