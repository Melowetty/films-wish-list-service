package ru.melowetty.filmswishlistservice.model

import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.Notification

data class KafkaNotification(
    val user: UserInfo,
    val notification: Notification
)
