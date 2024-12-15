package ru.melowetty.filmswishlistservice.service

import org.springframework.security.core.userdetails.UserDetailsService
import ru.melowetty.filmswishlistservice.dto.UserDto
import ru.melowetty.filmswishlistservice.entity.UserEntity

interface UserService : UserDetailsService {
    fun createBasicUser(username: String, password: String): UserDto
    fun getUserInfo(): UserDto
    fun getUserByAuth(): UserEntity
}