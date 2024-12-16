package ru.melowetty.filmswishlistservice.dto

import java.time.LocalDate
import ru.melowetty.filmswishlistservice.model.Rating

abstract class UserMovieDto(
    open val imdbId: String,
    open val title: String,
    open val description: String?,
    open val rating: Rating?,
    open val year: Int,
    open val released: LocalDate,
    open val genres: List<GenreDto>,
    open val countries: List<CountryDto>,
    open val directors: List<DirectorDto>,
    open val writers: List<WriterDto>,
    open val actors: List<ActorDto>,
    open val languages: List<LanguageDto>,
    open val durationInMinutes: Int?,
    open val posterLink: String?,
    open val imdbRating: Float?,
    open val isWished: Boolean,
    open val wishDetails: WishDetailsDto?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserMovieDto

        if (title != other.title) return false
        if (description != other.description) return false
        if (imdbId != other.imdbId) return false
        if (rating != other.rating) return false
        if (released != other.released) return false
        if (genres != other.genres) return false
        if (countries != other.countries) return false
        if (directors != other.directors) return false
        if (writers != other.writers) return false
        if (actors != other.actors) return false
        if (languages != other.languages) return false
        if (durationInMinutes != other.durationInMinutes) return false
        if (posterLink != other.posterLink) return false
        if (imdbRating != other.imdbRating) return false
        if (isWished != other.isWished) return false
        if (wishDetails != other.wishDetails) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imdbId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + released.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + countries.hashCode()
        result = 31 * result + directors.hashCode()
        result = 31 * result + writers.hashCode()
        result = 31 * result + actors.hashCode()
        result = 31 * result + languages.hashCode()
        result = 31 * result + durationInMinutes.hashCode()
        result = 31 * result + posterLink.hashCode()
        result = 31 * result + imdbRating.hashCode()
        result = 31 * result + isWished.hashCode()
        result = 31 * result + wishDetails.hashCode()
        return result
    }


}