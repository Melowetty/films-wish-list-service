package ru.melowetty.filmswishlistservice.notification.model

import java.time.LocalDate
import ru.melowetty.filmswishlistservice.notification.Notification

data class MovieChanges(
    val movieTitle: String,
    val newDate: LocalDate
) : Notification