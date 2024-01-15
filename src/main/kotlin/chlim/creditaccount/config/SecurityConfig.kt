package chlim.creditaccount.config

import chlim.creditaccount.auth.kakao.OAuth2AuthenticationFailureHandler
import chlim.creditaccount.auth.kakao.OAuth2AuthenticationSuccessHandler
import chlim.creditaccount.auth.kakao.OAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val oAuth2UserService: OAuth2UserService,
    private val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
    private val oAuth2AuthenticationFailureHandler: OAuth2AuthenticationFailureHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            cors {
                configurationSource = corsConfigurationSource()
            }
            csrf {
                disable()
            }
            httpBasic {
                disable()
            }
            headers {
                frameOptions {
                    sameOrigin = true
                }
            }
            formLogin {
                disable()
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            oauth2Login {
                authenticationSuccessHandler = oAuth2AuthenticationSuccessHandler
                authenticationFailureHandler = oAuth2AuthenticationFailureHandler
                userInfoEndpoint {
                    userService = oAuth2UserService
                }
            }
        }

        return http.build();
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("http://localhost:3000")
        configuration.addAllowedMethod("*")
        configuration.addAllowedHeader("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }
}
