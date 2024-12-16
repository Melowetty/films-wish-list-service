package ru.melowetty.filmswishlistservice.notification.model

import java.time.LocalDate
import ru.melowetty.filmswishlistservice.annotation.NoArg

@NoArg
data class MovieChanges(
    val movieTitle: String,
    val newDate: LocalDate
)