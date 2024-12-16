package ru.melowetty.filmswishlistservice.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import ru.melowetty.filmswishlistservice.annotation.NoArg

@NoArg
abstract class ExternalMovie(
    open val imdbId: String,
    open val title: LocalizedData,
    open val description: LocalizedData?,
    open val rating: Rating?,
    open val year: Int,
    @JsonFormat(pattern = "yyyy-MM-dd")
    open val released: LocalDate,
    open val genres: List<LocalizedData>,
    open val countries: List<LocalizedData>,
    open val directors: List<LocalizedData>,
    open val writers: List<LocalizedData>,
    open val actors: List<LocalizedData>,
    open val languages: List<LocalizedData>,
    open val durationInMinutes: Int?,
    open val posterLink: String?,
    open val imdbRating: Float?,
    open val type: MovieType
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExternalMovie

        if (imdbId != other.imdbId) return false
        if (title != other.title) return false
        if (description != other.description) return false
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
        result = 31 * result + description.hashCode()
        result = 31 * result + rating.hashCode()
        year?.let { result = 31 * result + it }
        result = 31 * result + released.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + countries.hashCode()
        result = 31 * result + directors.hashCode()
        result = 31 * result + writers.hashCode()
        result = 31 * result + actors.hashCode()
        result = 31 * result + languages.hashCode()
        durationInMinutes?.let { result = 31 * result + it.hashCode() }
        result = 31 * result + posterLink.hashCode()
        result = 31 * result + imdbRating.hashCode()
        return result
    }
}