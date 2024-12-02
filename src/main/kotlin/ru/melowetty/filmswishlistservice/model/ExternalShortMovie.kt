package ru.melowetty.filmswishlistservice.model

data class ExternalShortMovie(
    val title: String,
    val imdbId: String,
    val year: Int,
    val rating: Rating,
    val genres: List<String>,
    val countries: List<String>,
    val directors: List<String>,
    val actors: List<String>,
    val durationInMinutes: Int,
    val posterLink: String,
    val imdbRating: Float,
    val type: MovieType
)
