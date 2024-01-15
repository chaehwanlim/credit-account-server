package chlim.creditaccount.security

import chlim.creditaccount.domain.refreshtoken.RefreshToken
import chlim.creditaccount.domain.refreshtoken.repository.RefreshTokenRepository
import chlim.creditaccount.domain.user.User
import chlim.creditaccount.domain.user.exception.UserNotFoundException
import chlim.creditaccount.domain.user.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.InitializingBean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.security.Key
import java.util.*

@Component
class JwtProvider(
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository
) : InitializingBean {

    companion object {
        private const val JWT_SECRET =
            "9c0071a3ccf9b9cb6d9b1fb2bd3fa1ea9c7e44225715c1634e0426347f4e89b2e65cce2b9806eb335d77967de8796f5adf62f3e58ffbffeb8e5e06076ba14f54"
        private const val ACCESS_TOKEN_EXPIRATION = 30 * 60 * 1000L
        private const val REFRESH_TOKEN_EXPIRATION = 30 * 24 * 60 * 60 * 1000L
        private lateinit var KEY: Key
    }

    override fun afterPropertiesSet() {
        KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET))
    }

    @Transactional
    fun setNewToken(request: HttpServletRequest, response: HttpServletResponse, user: User) {
        setAccessToken(response, user)
        setRefreshToken(response, user)
    }

    fun generateAccessToken(user: User): String {
        val now = Date()
        val expiryDate = Date(now.time + ACCESS_TOKEN_EXPIRATION)

        val claims = mapOf("id" to user.id)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(KEY, SignatureAlgorithm.HS512)
            .compact()
    }

    fun resolveAccessToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    fun generateRefreshToken(user: User): String {
        val now = Date()
        val expiryDate = Date(now.time + REFRESH_TOKEN_EXPIRATION)

        val claims = mapOf("id" to user.id)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(KEY, SignatureAlgorithm.HS512)
            .compact()
    }

    fun resolveRefreshToken(request: HttpServletRequest): String? {
        return request.getHeader("Refresh-Token")
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token)

            claims.body.expiration.after(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token)
        val userId = (claims.body["id"] as Int).toLong()

        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }
        val userDetails = UserDetailsImpl(user)

        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }

    private fun setAccessToken(response: HttpServletResponse, user: User) {
        val token = generateAccessToken(user)
        response.setHeader("Authorization", "Bearer $token")

        val cookie = Cookie("AccessToken", token)

        cookie.isHttpOnly = true
        cookie.secure = true
        cookie.maxAge = 30 * 60 * 60 * 1000
        cookie.path = "/"
        cookie.setAttribute("SameSite", "None")

        response.addCookie(cookie)
    }

    private fun setRefreshToken(response: HttpServletResponse, user: User) {
        val token = generateRefreshToken(user)
        response.setHeader("Refresh-Token", token)
        refreshTokenRepository.save(RefreshToken(token))
    }

    private fun extractClaims(token: String): Claims = Jwts.parserBuilder()
        .setSigningKey(KEY)
        .build()
        .parseClaimsJws(token)
        .body
}
