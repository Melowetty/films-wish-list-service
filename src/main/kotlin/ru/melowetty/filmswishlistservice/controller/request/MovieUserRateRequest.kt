package ru.melowetty.filmswishlistservice.controller.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class MovieUserRateRequest(
    @Min(1, message = "{movie.rate.request.min-rate}")
    @Max(10, message = "{movie.rate.request.max-rate}")
    @Schema(description = "Оценка пользователя", example = "5")
    val rate: Int
)
