package chlim.creditaccount.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            .and().csrf()
                .ignoringAntMatchers("/h2-console/**")
            .and().headers()
                .frameOptions().sameOrigin()
            .and().formLogin()
                .disable();

        return http.build();
    }

}
