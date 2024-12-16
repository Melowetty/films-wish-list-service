package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация о фильме из виш листа")
data class WishDetailsDto(
    @Schema(description = "Отметка о просмотре фильма", example = "true")
    val isWatched: Boolean,
    @Schema(description = "Пользовательская оценка фильма", example = "8")
    val userRating: Int?
)
