package ru.melowetty.filmswishlistservice.model

import ru.melowetty.filmswishlistservice.annotation.NoArg

@NoArg
data class ExternalShortMovie(
    val title: LocalizedData,
    val imdbId: String,
    val year: Int,
    val lastYear: Int?,
    val rating: Rating?,
    val genres: List<LocalizedData>?,
    val countries: List<LocalizedData>?,
    val directors: List<LocalizedData>?,
    val actors: List<LocalizedData>?,
    val durationInMinutes: Int?,
    val posterLink: String?,
    val imdbRating: Float?,
    val type: MovieType
)
