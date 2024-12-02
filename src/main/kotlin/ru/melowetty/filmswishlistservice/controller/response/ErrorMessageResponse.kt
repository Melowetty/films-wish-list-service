package ru.melowetty.filmswishlistservice.controller.response

data class ErrorMessageResponse(
    val status: Int,
    val errorType: String,
    val errors: List<Any>,
)
