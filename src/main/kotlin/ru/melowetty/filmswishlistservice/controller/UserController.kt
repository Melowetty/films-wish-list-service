package ru.melowetty.filmswishlistservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.melowetty.filmswishlistservice.dto.UserDto
import ru.melowetty.filmswishlistservice.service.UserService

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("me")
    fun getUserInfo(): UserDto {
        return userService.getUserInfo()
    }

    @PatchMapping("settings")
    fun patchUser(@RequestBody request: HashMap<String, Any?>): UserDto {
        return userService.patchUser(request)
    }
}