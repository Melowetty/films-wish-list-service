package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Страна")
data class CountryDto(
    @Schema(description = "Идентификатор")
    val id: Long,
    @Schema(description = "Название страны на языке пользователя", example = "США")
    val name: String
)
