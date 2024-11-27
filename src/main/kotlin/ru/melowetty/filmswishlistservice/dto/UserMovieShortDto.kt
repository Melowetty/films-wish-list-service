package ru.melowetty.filmswishlistservice.dto

import ru.melowetty.filmswishlistservice.model.Rating

abstract class UserMovieShortDto {
    abstract val title: String
    abstract val imdbId: String
    abstract val year: Int
    abstract val rating: Rating
    abstract val genres: List<String>
    abstract val countries: List<String>
    abstract val directors: List<String>
    abstract val actors: List<String>
    abstract val durationInMinutes: Int
    abstract val posterLink: String
    abstract val imdbRating: Float
    abstract val isWished: Boolean
    abstract val wishDetails: WishDetailsDto?

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserMovieShortDto

        if (title != other.title) return false
        if (imdbId != other.imdbId) return false
        if (year != other.year) return false
        if (rating != other.rating) return false
        if (genres != other.genres) return false
        if (countries != other.countries) return false
        if (directors != other.directors) return false
        if (actors != other.actors) return false
        if (durationInMinutes != other.durationInMinutes) return false
        if (posterLink != other.posterLink) return false
        if (imdbRating != other.imdbRating) return false
        if (isWished != other.isWished) return false
        if (wishDetails != other.wishDetails) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + imdbId.hashCode()
        result = 31 * result + year.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + countries.hashCode()
        result = 31 * result + directors.hashCode()
        result = 31 * result + actors.hashCode()
        result = 31 * result + durationInMinutes
        result = 31 * result + posterLink.hashCode()
        result = 31 * result + imdbRating.hashCode()
        result = 31 * result + isWished.hashCode()
        result = 31 * result + wishDetails.hashCode()

        return result
    }


}