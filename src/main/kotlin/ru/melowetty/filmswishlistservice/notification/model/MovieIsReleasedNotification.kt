package ru.melowetty.filmswishlistservice.notification.model

import ru.melowetty.filmswishlistservice.notification.Notification

data class MovieIsReleasedNotification(
    val titles: List<String>,
): Notification
