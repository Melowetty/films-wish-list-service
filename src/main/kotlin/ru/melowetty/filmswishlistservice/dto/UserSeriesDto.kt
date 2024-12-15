package ru.melowetty.filmswishlistservice.dto

import java.time.LocalDate
import java.time.LocalDateTime
import ru.melowetty.filmswishlistservice.model.Rating

data class UserSeriesDto(
    override val title: String,
    override val description: String?,
    override val imdbId: String,
    override val rating: Rating?,
    override val year: Int,
    override val released: LocalDate,
    override val genres: List<GenreDto>,
    override val countries: List<CountryDto>,
    override val directors: List<DirectorDto>,
    override val writers: List<WriterDto>,
    override val actors: List<ActorDto>,
    override val languages: List<LanguageDto>,
    override val durationInMinutes: Int?,
    override val posterLink: String?,
    override val imdbRating: Float?,
    override val isWished: Boolean,
    override val wishDetails: WishDetailsDto,
    val seasonsCount: Int?,
) : UserMovieDto(
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
    isWished,
    wishDetails,
)
