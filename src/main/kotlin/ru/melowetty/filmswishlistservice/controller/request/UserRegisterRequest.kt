package ru.melowetty.filmswishlistservice.controller.request

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class UserRegisterRequest(
    @Length(min = 3, max = 255)
    @NotBlank
    val username: String,
    
    @Length(min = 8)
    @NotBlank
    val password: String,
)
