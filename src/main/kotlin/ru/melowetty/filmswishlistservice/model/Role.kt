package ru.melowetty.filmswishlistservice.model

import org.springframework.security.core.GrantedAuthority

enum class Role(name: String) : GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    override fun getAuthority(): String {
        return name
    }
}