package ru.melowetty.filmswishlistservice.controller.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import ru.melowetty.filmswishlistservice.model.Language

data class UserRegisterRequest(
    @Length(min = 3, max = 255)
    @NotBlank
    @Schema(description = "Никнейм пользователя")
    val username: String,
    
    @Length(min = 8)
    @NotBlank
    @Schema(description = "Пароль пользователя")
    val password: String,

    @Schema(description = "Язык пользователя", example = "RUSSIAN")
    val language: Language,
)
