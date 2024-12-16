package ru.melowetty.filmswishlistservice.dto

import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.Provider

data class UserInfo(
    val username: String,
    val provider: Provider,
    val language: Language,
    val telegramId: Long?
)