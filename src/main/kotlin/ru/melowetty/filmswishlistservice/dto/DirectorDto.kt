package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Режиссёр")
data class DirectorDto(
    @Schema(description = "Идентификатор")
    val id: Long,
    @Schema(description = "Имя режиссёра на языке пользователя", example = "Кристофер Нолан")
    val name: String
)
