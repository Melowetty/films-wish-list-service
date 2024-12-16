package ru.melowetty.filmswishlistservice.notification.model

import ru.melowetty.filmswishlistservice.notification.Notification

data class SeriesChanges(
    val title: String,
    val lastYear: Int
): Notification
