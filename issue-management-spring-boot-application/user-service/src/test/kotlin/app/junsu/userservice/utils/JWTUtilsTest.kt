package app.junsu.userservice.utils

import app.junsu.userservice.config.JWTProperties
import com.auth0.jwt.interfaces.DecodedJWT
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class JWTUtilsTest {

    private val logger = KotlinLogging.logger {}

    @Test
    fun createTokenTest() {
        val jwtClaim = JWTClaim(
            userId = 1,
            email = "test@gmail.com",
            profileUrl = "profile.jpg",
            username = "junsuPark",
        )

        val properties = JWTProperties(
            issuer = "junsu",
            subject = "auth",
            expiresTime = 3600,
            secret = "some-secret"
        )

        val token = JWTUtils.createToken(
            claim = jwtClaim,
            properties = properties,
        )

        assertNotNull(token)

        logger.info { "token : $token" }
    }

    @Test
    fun decodeTest() {
        val jwtClaim = JWTClaim(
            userId = 1,
            email = "test@gmail.com",
            profileUrl = "profile.jpg",
            username = "junsuPark",
        )

        val properties = JWTProperties(
            issuer = "junsu",
            subject = "auth",
            expiresTime = 3600,
            secret = "some-secret"
        )

        val token = JWTUtils.createToken(
            claim = jwtClaim,
            properties = properties,
        )

        val decodedJwt: DecodedJWT = JWTUtils.decode(
            token = token,
            secret = properties.secret,
            issuer = properties.issuer,
        )

        with(decodedJwt) {
            logger.info { "claims : $claims" }

            val userId = claims["userId"]!!.asLong()
            assertEquals(userId, jwtClaim.userId)

            val email = claims["email"]!!.asString()
            assertEquals(email, jwtClaim.email)

            val profileUrl = claims["profileUrl"]!!.asString()
            assertEquals(profileUrl, jwtClaim.profileUrl)

            val username = claims["username"]!!.asString()
            assertEquals(username, jwtClaim.username)
        }
    }
}
