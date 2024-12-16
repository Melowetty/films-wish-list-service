package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import java.time.LocalDate
import ru.melowetty.filmswishlistservice.model.Rating

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class SeriesEntity(
    imdbId: String,
    title: LocalizedEntity,
    year: Int,
    rating: Rating?,
    released: LocalDate,
    genres: MutableList<GenreEntity> = mutableListOf(),
    countries: MutableList<CountryEntity> = mutableListOf(),
    directors: MutableList<DirectorEntity> = mutableListOf(),
    writers: MutableList<WriterEntity> = mutableListOf(),
    actors: MutableList<ActorEntity> = mutableListOf(),
    languages: MutableList<LanguageEntity> = mutableListOf(),
    durationInMinutes: Int?,
    description: LocalizedEntity?,
    posterLink: String?,
    imdbRating: Float?,

    @Column(name = "seasons_count")
    var seasonsCount: Int?,

    @Column(name = "last_year")
    var lastYear: Int,
    ) : MovieEntity(imdbId, title, year, rating, released, genres, countries,
    directors, writers, actors, languages,
    durationInMinutes = durationInMinutes,
        description = description,
        posterLink = posterLink,
        imdbRating = imdbRating
)