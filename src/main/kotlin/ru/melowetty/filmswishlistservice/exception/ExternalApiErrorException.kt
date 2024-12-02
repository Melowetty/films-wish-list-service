package ru.melowetty.filmswishlistservice.exception

import org.springframework.http.HttpStatus

class ExternalApiErrorException(
    template: String,
    params: Array<Any> = arrayOf()
    ) : LocalizedException(template = template,
    status = HttpStatus.INTERNAL_SERVER_ERROR, code = "EXTERNAL_API_ERROR", params = params)