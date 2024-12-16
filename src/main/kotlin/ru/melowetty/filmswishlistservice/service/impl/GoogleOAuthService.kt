package ru.melowetty.filmswishlistservice.service.impl

import kotlin.jvm.optionals.getOrNull
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.UserEntity
import ru.melowetty.filmswishlistservice.model.GoogleOAuthUser
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.Provider
import ru.melowetty.filmswishlistservice.model.Role
import ru.melowetty.filmswishlistservice.repository.UserRepository

@Service
class GoogleOAuthService(
    private val userRepository: UserRepository,
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val user = super.loadUser(userRequest)
        val email = user.getAttribute<String>("email")
            ?: throw AuthenticationServiceException("Не достаточно данных для аутентификации")

        val userFromDb = userRepository.findByUsername(email).getOrNull()

        if (userFromDb == null) {

            val authorities = mutableListOf(Role.USER)

            val userEntity = UserEntity(
                username = email,
                password = Provider.GOOGLE.name,
                provider = Provider.GOOGLE,
                roles = authorities,
                language = Language.RUSSIAN,
                telegramId = null,
            )

            userRepository.save(userEntity)

            return GoogleOAuthUser(user, authorities.toList())
        }

        return GoogleOAuthUser(user, userFromDb.authorities.toList())
    }
}