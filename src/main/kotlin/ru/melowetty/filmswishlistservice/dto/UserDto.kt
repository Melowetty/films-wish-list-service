package ru.melowetty.filmswishlistservice.dto

import java.time.LocalDateTime
import ru.melowetty.filmswishlistservice.model.Provider
import ru.melowetty.filmswishlistservice.model.Role

data class UserDto(
    val username: String,
    val provider: Provider,
    val roles: List<Role>,
    val created: LocalDateTime,
    val edited: LocalDateTime,
)
