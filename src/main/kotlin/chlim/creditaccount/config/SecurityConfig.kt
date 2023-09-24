package chlim.creditaccount.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize("/h2-console/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            csrf {
                ignoringRequestMatchers("/h2-console/**")
            }
            headers {
                frameOptions {
                    sameOrigin
                }
            }
            formLogin {
                disable()
            }
        }

        return http.build();
    }

}
