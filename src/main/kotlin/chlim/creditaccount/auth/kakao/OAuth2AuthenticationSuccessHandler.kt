package chlim.creditaccount.auth.kakao

import chlim.creditaccount.domain.user.exception.UserNotFoundException
import chlim.creditaccount.domain.user.repository.UserRepository
import chlim.creditaccount.security.JwtProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2AuthenticationSuccessHandler(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuth2User = authentication.principal as DefaultOAuth2User
        val attributes = oAuth2User.attributes

        val user = (attributes["userId"] as Long).let {
            userRepository.findById(it).orElseThrow { UserNotFoundException(it) }
        }

        jwtProvider.setNewToken(request, response, user)
        response.setHeader("Location", "http://localhost:3000/login/kakao/success")
        response.status = HttpServletResponse.SC_FOUND
    }
}
