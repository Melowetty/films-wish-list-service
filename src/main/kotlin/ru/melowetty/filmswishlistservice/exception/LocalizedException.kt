package ru.melowetty.filmswishlistservice.exception

import org.springframework.http.HttpStatusCode

open class LocalizedException(
    val template: String,
    val params: Array<Any> = arrayOf(),
    val status: HttpStatusCode,
    val code: String,
) : RuntimeException()