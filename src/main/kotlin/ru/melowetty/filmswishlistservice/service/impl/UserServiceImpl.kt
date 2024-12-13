package ru.melowetty.filmswishlistservice.service.impl

import kotlin.jvm.optionals.getOrNull
import org.springframework.context.annotation.Primary
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserDto
import ru.melowetty.filmswishlistservice.entity.UserEntity
import ru.melowetty.filmswishlistservice.exception.InvalidUserDataException
import ru.melowetty.filmswishlistservice.exception.UserNotFoundException
import ru.melowetty.filmswishlistservice.mapper.UserMapper
import ru.melowetty.filmswishlistservice.model.GoogleOAuthUser
import ru.melowetty.filmswishlistservice.model.Provider
import ru.melowetty.filmswishlistservice.model.Role
import ru.melowetty.filmswishlistservice.repository.UserRepository
import ru.melowetty.filmswishlistservice.service.UserService

@Service
@Primary
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userMapper: UserMapper
) : UserService {
    override fun createBasicUser(username: String, password: String): UserDto {
        val encodedPassword = passwordEncoder.encode(password)

        val user = UserEntity(
            username = username,
            password = encodedPassword,
            provider = Provider.BASIC,
            roles = mutableListOf(Role.USER)
        )

        val entity = userRepository.save(user)

        return userMapper.toDto(entity)
    }

    override fun getUserInfo(): UserDto {
        val user = SecurityContextHolder.getContext().authentication.principal

        var username: String? = null

        if (user is GoogleOAuthUser) {
            username = user.getEmail()
        }

        val entity: UserEntity? = username?.let {
            userRepository.findByUsername(it).getOrNull()
        }

        if (entity == null) {
            SecurityContextHolder.getContext().authentication.isAuthenticated = false
            throw InvalidUserDataException("exception.invalid-user-data")
        }

        return userMapper.toDto(entity)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).orElseThrow {
            throw UserNotFoundException("exception.user-not-found.by-username")
        }
    }
}