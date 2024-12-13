package ru.melowetty.filmswishlistservice.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class GoogleOAuthUser(
    private val oAuth2User: OAuth2User,
    private val authorities: List<GrantedAuthority>
) : OAuth2User {
    override fun getName(): String {
        return oAuth2User.name
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return oAuth2User.attributes
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.toMutableList()
    }

    fun getEmail(): String {
        return oAuth2User.getAttribute<String>("email")!!
    }
}