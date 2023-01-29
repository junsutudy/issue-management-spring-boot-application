package app.junsu.userservice.domain.repository.user

import app.junsu.userservice.domain.entity.user.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, Long> {

    suspend fun findByEmail(email: String): User?
}
