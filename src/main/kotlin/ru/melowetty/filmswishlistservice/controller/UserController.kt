package ru.melowetty.filmswishlistservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.melowetty.filmswishlistservice.dto.UserDto
import ru.melowetty.filmswishlistservice.service.UserService

@RestController
@Tag(name = "Работа с пользователями")
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {
    @Operation(description = "Получить информацию о текущем пользователе")
    @GetMapping("me")
    fun getUserInfo(): UserDto {
        return userService.getUserInfo()
    }

    @Operation(description = "Частично изменить настройки")
    @PatchMapping("settings")
    fun patchUser(@RequestBody request: HashMap<String, Any?>): UserDto {
        return userService.patchUser(request)
    }
}