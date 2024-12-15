package ru.melowetty.filmswishlistservice.model

import java.time.LocalDate
import ru.melowetty.filmswishlistservice.annotation.NoArg

@NoArg
data class ExternalFilm(
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
    override val type: MovieType = MovieType.FILM,
    val boxOffice: Long?,
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
