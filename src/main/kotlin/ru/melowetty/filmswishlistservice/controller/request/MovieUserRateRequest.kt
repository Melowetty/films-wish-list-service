package ru.melowetty.filmswishlistservice.controller.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class MovieUserRateRequest(
    @Min(1, message = "{movie.rate.request.min-rate}")
    @Max(10, message = "{movie.rate.request.max-rate}")
    val rate: Int
)
