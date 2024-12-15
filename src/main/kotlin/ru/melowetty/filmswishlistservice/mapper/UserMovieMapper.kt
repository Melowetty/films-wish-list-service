package ru.melowetty.filmswishlistservice.mapper

import mu.KotlinLogging
import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.Extensions.Companion.toLocalRatingSystem
import ru.melowetty.filmswishlistservice.dto.UserFilmShortDto
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.dto.UserSeriesShortDto
import ru.melowetty.filmswishlistservice.dto.WishDetailsDto
import ru.melowetty.filmswishlistservice.entity.FilmEntity
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.SeriesEntity
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class UserMovieMapper {
    fun toDto(entity: WishMovieEntity): UserMovieDto {
        TODO()
    }

    fun movieToDto(entity: MovieEntity): UserMovieDto {
TODO()
    }

    fun toShortDto(entity: WishMovieEntity): UserMovieShortDto {
        val movie = entity.movie
        val lang = entity.user.language

        val wishDetails = WishDetailsDto(
            isWatched = entity.isWatched,
            userRating = entity.userRating
        )

        if (movie is FilmEntity) {
            return UserFilmShortDto(
                title = movie.title.getValueByLanguage(lang),
                imdbId = movie.imdbId,
                year = movie.year,
                rating = movie.rating?.toLocalRatingSystem(lang),
                genres = movie.genres.map { it.name.getValueByLanguage(lang) },
                countries = movie.countries.map { it.name.getValueByLanguage(lang) },
                directors = movie.directors.map { it.name.getValueByLanguage(lang) },
                actors = movie.actors.map { it.name.getValueByLanguage(lang) },
                writers = movie.writers.map { it.name.getValueByLanguage(lang) },
                languages = movie.languages.map { it.name.getValueByLanguage(lang) },
                durationInMinutes = movie.durationInMinutes,
                posterLink = movie.posterLink,
                imdbRating = movie.imdbRating,
                boxOffice = movie.boxOffice,
                isWished = true,
                wishDetails = wishDetails
            )
        }

        else {
            movie as SeriesEntity

            return UserSeriesShortDto(
                title = movie.title.getValueByLanguage(lang),
                imdbId = movie.imdbId,
                year = movie.year,
                rating = movie.rating?.toLocalRatingSystem(lang),
                genres = movie.genres.map { it.name.getValueByLanguage(lang) },
                countries = movie.countries.map { it.name.getValueByLanguage(lang) },
                directors = movie.directors.map { it.name.getValueByLanguage(lang) },
                actors = movie.actors.map { it.name.getValueByLanguage(lang) },
                writers = movie.writers.map { it.name.getValueByLanguage(lang) },
                languages = movie.languages.map { it.name.getValueByLanguage(lang) },
                durationInMinutes = movie.durationInMinutes,
                posterLink = movie.posterLink,
                imdbRating = movie.imdbRating,
                seasonCount = movie.seasonsCount,
                isWished = true,
                wishDetails = wishDetails
            )
        }
    }

    fun movieToShortDto(lang: Language, entity: MovieEntity): UserMovieShortDto {
        val movie = entity

        if (movie is FilmEntity) {
            return UserFilmShortDto(
                title = movie.title.getValueByLanguage(lang),
                imdbId = movie.imdbId,
                year = movie.year,
                rating = movie.rating?.toLocalRatingSystem(lang),
                genres = movie.genres.map { it.name.getValueByLanguage(lang) },
                countries = movie.countries.map { it.name.getValueByLanguage(lang) },
                directors = movie.directors.map { it.name.getValueByLanguage(lang) },
                actors = movie.actors.map { it.name.getValueByLanguage(lang) },
                writers = movie.writers.map { it.name.getValueByLanguage(lang) },
                languages = movie.languages.map { it.name.getValueByLanguage(lang) },
                durationInMinutes = movie.durationInMinutes,
                posterLink = movie.posterLink,
                imdbRating = movie.imdbRating,
                boxOffice = movie.boxOffice,
                isWished = false,
                wishDetails = null
            )
        }

        else {
            movie as SeriesEntity

            return UserSeriesShortDto(
                title = movie.title.getValueByLanguage(lang),
                imdbId = movie.imdbId,
                year = movie.year,
                rating = movie.rating?.toLocalRatingSystem(lang),
                genres = movie.genres.map { it.name.getValueByLanguage(lang) },
                countries = movie.countries.map { it.name.getValueByLanguage(lang) },
                directors = movie.directors.map { it.name.getValueByLanguage(lang) },
                actors = movie.actors.map { it.name.getValueByLanguage(lang) },
                writers = movie.writers.map { it.name.getValueByLanguage(lang) },
                languages = movie.languages.map { it.name.getValueByLanguage(lang) },
                durationInMinutes = movie.durationInMinutes,
                posterLink = movie.posterLink,
                imdbRating = movie.imdbRating,
                seasonCount = movie.seasonsCount,
                isWished = false,
                wishDetails = null
            )
        }
    }
}