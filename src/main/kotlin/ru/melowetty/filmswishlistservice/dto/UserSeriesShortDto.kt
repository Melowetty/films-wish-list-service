package ru.melowetty.filmswishlistservice.dto

import ru.melowetty.filmswishlistservice.model.Rating

data class UserSeriesShortDto(
    override val title: String,
    override val imdbId: String,
    override val year: Int,
    override val rating: Rating?,
    override val genres: List<String>,
    override val countries: List<String>,
    override val directors: List<String>,
    override val actors: List<String>,
    override val writers: List<String>,
    override val languages: List<String>,
    override val durationInMinutes: Int?,
    override val posterLink: String?,
    override val imdbRating: Float?,
    val seasonCount: Int?,
    override val isWished: Boolean,
    override val wishDetails: WishDetailsDto?,
): UserMovieShortDto(
    title,
    imdbId,
    year,
    rating,
    genres,
    countries,
    directors,
    actors,
    writers,
    languages,
    durationInMinutes,
    posterLink,
    imdbRating,
    isWished,
    wishDetails
)
