package ru.melowetty.filmswishlistservice.service

import org.springframework.security.core.userdetails.UserDetailsService
import ru.melowetty.filmswishlistservice.dto.UserDto

interface UserService : UserDetailsService {
    fun createBasicUser(username: String, password: String): UserDto
    fun getUserInfo(): UserDto
}