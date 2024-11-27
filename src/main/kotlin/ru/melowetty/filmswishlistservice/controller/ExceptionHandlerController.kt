package ru.melowetty.filmswishlistservice.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.annotation.HandlerMethodValidationException
import ru.melowetty.filmswishlistservice.controller.response.Error
import ru.melowetty.filmswishlistservice.controller.response.ErrorMessageResponse

@ControllerAdvice
class ExceptionHandlerController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(e: HandlerMethodValidationException): ResponseEntity<ErrorMessageResponse> {
        val errors: MutableList<Error> = ArrayList()
        for (result in e.allValidationResults) {
            val param = result.methodParameter.parameterName ?: continue
            for (error in result.resolvableErrors) {
                errors.add(Error(param, error.defaultMessage ?: continue))
            }
        }

        return ResponseEntity.badRequest().body(ErrorMessageResponse(HttpStatus.BAD_REQUEST.value(), errors))
    }
}