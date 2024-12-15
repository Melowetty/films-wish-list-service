package ru.melowetty.filmswishlistservice.model

import java.time.LocalDate

data class ExternalSeries(
    override val imdbId: String,
    override val title: LocalizedData,
    override val description: LocalizedData?,
    override val rating: Rating?,
    override val year: Int,
    override val released: LocalDate,
    override val genres: List<LocalizedData>,
    override val countries: List<LocalizedData>,
    override val directors: List<LocalizedData>,
    override val writers: List<LocalizedData>,
    override val actors: List<LocalizedData>,
    override val languages: List<LocalizedData>,
    override val durationInMinutes: Int?,
    override val posterLink: String?,
    override val imdbRating: Float?,
    override val type: MovieType = MovieType.SERIES,
    val seasonsCount: Int?,
    val lastYear: Int,
) : ExternalMovie(
    imdbId,
    title,
    description,
    rating,
    year,
    released,
    genres,
    countries,
    directors,
    writers,
    actors,
    languages,
    durationInMinutes,
    posterLink,
    imdbRating,
    type
)
