package ru.melowetty.filmswishlistservice.model

import ru.melowetty.filmswishlistservice.annotation.NoArg
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.Notification

@NoArg
data class KafkaNotification(
    val user: UserInfo,
    val notification: Any,
    val cls: Class<out Notification>
)
