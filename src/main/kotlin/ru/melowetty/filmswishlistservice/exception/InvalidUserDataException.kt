package ru.melowetty.filmswishlistservice.exception

import org.springframework.http.HttpStatus

class InvalidUserDataException(template: String) : LocalizedException(
    template,
    status = HttpStatus.BAD_REQUEST,
    code = "INVALID_USER_DATA"
)