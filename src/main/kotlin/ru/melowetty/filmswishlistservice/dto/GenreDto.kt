package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Жанр")
data class GenreDto(
    @Schema(description = "Идентификатор")
    val id: Long,
    @Schema(description = "Нзавание жанра на языке пользователя", example = "Экшн")
    val name: String
)
