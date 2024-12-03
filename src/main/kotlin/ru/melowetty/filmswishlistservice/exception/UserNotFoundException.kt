package ru.melowetty.filmswishlistservice.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(
    template: String,
    params: Array<Any> = arrayOf()
) : LocalizedException(
    template = template,
    params = params,
    status = HttpStatus.NOT_FOUND,
    code = "USER_NOT_FOUND"
)