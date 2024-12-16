package ru.melowetty.filmswishlistservice.model.notification

import ru.melowetty.filmswishlistservice.dto.UserInfo

interface Notification {
    val user: UserInfo
}