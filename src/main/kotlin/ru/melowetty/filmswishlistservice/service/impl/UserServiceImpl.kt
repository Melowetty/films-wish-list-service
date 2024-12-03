package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.exception.UserNotFoundException
import ru.melowetty.filmswishlistservice.repository.UserRepository
import ru.melowetty.filmswishlistservice.service.UserService

@Service
@Primary
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).orElseThrow {
            throw UserNotFoundException("exception.user-not-found.by-username")
        }
    }
}