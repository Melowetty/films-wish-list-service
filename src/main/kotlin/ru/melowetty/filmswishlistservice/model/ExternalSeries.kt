package ru.melowetty.filmswishlistservice.model

import java.time.LocalDateTime

data class ExternalSeries(
    override val imdbId: String,
    override val title: String,
    override val rating: Rating,
    override val year: Int,
    override val released: LocalDateTime,
    override val genres: List<String>,
    override val countries: List<String>,
    override val directors: List<String>,
    override val writers: List<String>,
    override val actors: List<String>,
    override val languages: List<String>,
    override val durationInMinutes: Int,
    override val posterLink: String,
    override val imdbRating: Float,
    override val type: MovieType = MovieType.SERIES,
    val seasonsCount: Int,
) : ExternalMovie()
