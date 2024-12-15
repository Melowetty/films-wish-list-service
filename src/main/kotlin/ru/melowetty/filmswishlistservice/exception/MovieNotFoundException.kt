package ru.melowetty.filmswishlistservice.exception

import org.springframework.http.HttpStatus

class MovieNotFoundException(
    template: String,
    params: Array<Any> = arrayOf()
) : LocalizedException(
    template = template,
    params = params,
    status = HttpStatus.NOT_FOUND,
    code = "MOVIE_NOT_FOUND"
)