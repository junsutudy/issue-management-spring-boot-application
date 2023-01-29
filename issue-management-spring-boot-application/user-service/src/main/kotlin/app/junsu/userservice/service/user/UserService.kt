package app.junsu.userservice.service.user

import app.junsu.userservice.domain.entity.user.User
import app.junsu.userservice.domain.repository.user.UserRepository
import app.junsu.userservice.exception.ServerException.UserExistException
import app.junsu.userservice.model.signup.SignUpRequest
import app.junsu.userservice.utils.hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired val userRepository: UserRepository,
) {

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
}
