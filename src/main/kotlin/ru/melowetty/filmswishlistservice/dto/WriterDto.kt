package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Сценарист")
data class WriterDto(
    @Schema(description = "Идентификатор")
    val id: Long,
    @Schema(description = "Имя сценариста на языке пользователя", example = "Иван Иванов")
    val name: String
)
