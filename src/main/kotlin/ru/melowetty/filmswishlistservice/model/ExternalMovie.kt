package ru.melowetty.filmswishlistservice.model

import java.time.LocalDateTime

abstract class ExternalMovie {
    abstract val imdbId: String
    abstract val title: String
    abstract val rating: Rating
    abstract val year: Int
    abstract val released: LocalDateTime
    abstract val genres: List<String>
    abstract val countries: List<String>
    abstract val directors: List<String>
    abstract val writers: List<String>
    abstract val actors: List<String>
    abstract val languages: List<String>
    abstract val durationInMinutes: Int
    abstract val posterLink: String
    abstract val imdbRating: Float
    abstract val type: MovieType
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExternalMovie

        if (imdbId != other.imdbId) return false
        if (title != other.title) return false
        if (rating != other.rating) return false
        if (year != other.year) return false
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

        return true
    }

    override fun hashCode(): Int {
        var result = imdbId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + year
        result = 31 * result + released.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + countries.hashCode()
        result = 31 * result + directors.hashCode()
        result = 31 * result + writers.hashCode()
        result = 31 * result + actors.hashCode()
        result = 31 * result + languages.hashCode()
        result = 31 * result + durationInMinutes
        result = 31 * result + posterLink.hashCode()
        result = 31 * result + imdbRating.hashCode()
        return result
    }
}