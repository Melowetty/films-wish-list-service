package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import java.time.LocalDateTime
import ru.melowetty.filmswishlistservice.model.Rating

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class FilmEntity(
    imdbId: String,
    title: String,
    year: Int,
    rating: Rating,
    released: LocalDateTime,
    genres: MutableSet<GenreEntity> = mutableSetOf(),
    countries: MutableSet<CountryEntity> = mutableSetOf(),
    directors: MutableList<DirectorEntity> = mutableListOf(),
    writers: MutableList<WriterEntity> = mutableListOf(),
    actors: MutableList<ActorEntity> = mutableListOf(),
    languages: MutableSet<LanguageEntity> = mutableSetOf(),
    durationInMinutes: Int,
    description: String,
    posterLink: String,
    imdbRating: Float,

    @Column(name = "box_office")
    var boxOffice: Long,
) : MovieEntity(imdbId, title, year, rating, released, genres, countries,
    directors, writers, actors, languages,
    durationInMinutes = durationInMinutes,
    description = description,
    posterLink = posterLink,
    imdbRating = imdbRating) {
}