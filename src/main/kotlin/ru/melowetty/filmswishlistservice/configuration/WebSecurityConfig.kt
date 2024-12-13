package ru.melowetty.filmswishlistservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import ru.melowetty.filmswishlistservice.service.UserService
import ru.melowetty.filmswishlistservice.service.impl.GoogleOAuthService


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
) {
    @Bean
    fun filterChain(http: HttpSecurity, oAuthService: GoogleOAuthService): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth -> auth
                .requestMatchers("/", "/auth**").permitAll()
                .anyRequest().authenticated()
            }
            .oauth2Login {
                it.userInfoEndpoint {
                    it.userService {
                        oAuthService.loadUser(it)
                    }
                }
            }
            .httpBasic(Customizer.withDefaults())
            .build()
    }

    @Bean
    fun authenticationProvider(userService: UserService): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()

        authProvider.setUserDetailsService(userService)
        authProvider.setPasswordEncoder(passwordEncoder())

        return authProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}