package ru.melowetty.filmswishlistservice.notification.model

import ru.melowetty.filmswishlistservice.notification.Notification

data class MoviesChangesNotification(
    val seriesChanges: List<SeriesChanges>,
    val movieChanges: List<MovieChanges>
): Notification()
