package ru.melowetty.filmswishlistservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.melowetty.filmswishlistservice.controller.request.UserRegisterRequest
import ru.melowetty.filmswishlistservice.service.UserService

@RestController
@RequestMapping("auth")
class AuthController(
    private val userService: UserService,
) {
    @PostMapping("register")
    fun register(@RequestBody request: UserRegisterRequest) {
        userService.createBasicUser(request.username, request.password)
    }
}