package ru.melowetty.filmswishlistservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.melowetty.filmswishlistservice.controller.request.UserRegisterRequest
import ru.melowetty.filmswishlistservice.service.UserService

@RestController
@Tag(name = "Аутентификация и авторизация")
@RequestMapping("auth")
class AuthController(
    private val userService: UserService,
) {
    @Operation(description = "Зарегистрироваться")
    @PostMapping("register")
    fun register(@RequestBody request: UserRegisterRequest) {
        userService.createBasicUser(request.username, request.password, request.language)
    }
}