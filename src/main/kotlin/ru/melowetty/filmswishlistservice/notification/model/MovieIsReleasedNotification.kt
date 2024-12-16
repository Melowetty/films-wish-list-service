package ru.melowetty.filmswishlistservice.notification.model

import ru.melowetty.filmswishlistservice.annotation.NoArg
import ru.melowetty.filmswishlistservice.notification.Notification

@NoArg
data class MovieIsReleasedNotification(
    val titles: List<String>,
): Notification()
