package ru.melowetty.filmswishlistservice.controller.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Сообщение об ошибке")
data class ErrorMessageResponse(
    @Schema(description = "HTTP статус-код ошибки", example = "404")
    val status: Int,
    @Schema(description = "Константное обозначение ошибки", example = "MOVIE_NOT_FOUND")
    val errorType: String,
    @Schema(description = "Описание ошибок")
    val errors: List<Any>,
)
