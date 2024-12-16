package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация об актёре")
data class ActorDto(
    @Schema(description = "Идентификатор")
    val id: Long,
    @Schema(description = "Имя актёра на языке пользователя", example = "Райан Гослинг")
    val name: String
)
