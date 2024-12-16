package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.Provider
import ru.melowetty.filmswishlistservice.model.Role

@Schema(description = "Информация о пользователе")
data class UserDto(
    @Schema(description = "Имя пользователя", example = "melowetty")
    val username: String,
    @Schema(description = "Тип аккаунта пользователя", example = "GOOGLE")
    val provider: Provider,
    @Schema(description = "Роли пользователя")
    val roles: List<Role>,
    @Schema(description = "Язык пользователя", example = "RUSSIAN")
    val language: Language,
    @Schema(description = "Telegram ID", example = "1231434535")
    val telegramId: Long?,
    @Schema(description = "Дата создания аккаунта")
    val created: LocalDateTime,
    @Schema(description = "Дата изменения аккаунта")
    val edited: LocalDateTime,
)
