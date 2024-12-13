package ru.melowetty.filmswishlistservice.controller

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.HandlerMethodValidationException
import ru.melowetty.filmswishlistservice.controller.response.ErrorMessageResponse
import ru.melowetty.filmswishlistservice.exception.LocalizedException

@ControllerAdvice
class ExceptionHandlerController(
    private val messageSource: MessageSource
) {
    @ExceptionHandler
    fun handleMethodArgumentNotValidException(e: HandlerMethodValidationException): ResponseEntity<ErrorMessageResponse> {
        val errors: MutableList<Error> = ArrayList()
        for (result in e.allValidationResults) {
            val param = result.methodParameter.parameterName ?: continue
            for (error in result.resolvableErrors) {
                errors.add(Error(param, error.defaultMessage ?: continue))
            }
        }

        return ResponseEntity.badRequest().body(
            ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION", errors
            )
        )
    }

    @ExceptionHandler
    fun handleLocalizedException(e: LocalizedException): ResponseEntity<ErrorMessageResponse> {
        val locale = LocaleContextHolder.getLocale()
        val localizedMessage = messageSource.getMessage(e.template, e.params, locale)

        return ResponseEntity.status(e.status).body(
            ErrorMessageResponse(
                e.status.value(),
                errorType = e.code, errors = listOf(localizedMessage)
            )
        )
    }

    data class Error(
        val target: String,
        val errorMessage: String,
    )
}