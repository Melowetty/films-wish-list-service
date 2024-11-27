package ru.melowetty.filmswishlistservice.controller.response

data class ErrorMessageResponse(
    val status: Int,
    val errors: List<Error>,
)

data class Error(
    val target: String,
    val errorMessage: String,
)
