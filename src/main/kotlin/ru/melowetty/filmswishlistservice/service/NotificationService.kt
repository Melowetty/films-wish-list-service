package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.Notification

interface NotificationService {
    fun notify(user: UserInfo, notification: Notification)
}