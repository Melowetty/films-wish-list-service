package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Язык")
data class LanguageDto(
    @Schema(description = "Идентификатор")
    val id: Long,
    @Schema(description = "Название языка на языке пользователя", example = "Английский")
    val name: String
)
