package chlim.creditaccount.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = jwtProvider.resolveAccessToken(request as HttpServletRequest)

        if (token != null && jwtProvider.validateToken(token)) {
            val authentication = jwtProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain?.doFilter(request, response)
    }
}
