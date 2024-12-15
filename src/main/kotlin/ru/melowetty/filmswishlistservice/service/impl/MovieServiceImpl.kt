package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.ActorEntity
import ru.melowetty.filmswishlistservice.entity.CountryEntity
import ru.melowetty.filmswishlistservice.entity.DirectorEntity
import ru.melowetty.filmswishlistservice.entity.FilmEntity
import ru.melowetty.filmswishlistservice.entity.GenreEntity
import ru.melowetty.filmswishlistservice.entity.LanguageEntity
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.SeriesEntity
import ru.melowetty.filmswishlistservice.entity.WriterEntity
import ru.melowetty.filmswishlistservice.exception.MovieNotFoundException
import ru.melowetty.filmswishlistservice.model.ExternalFilm
import ru.melowetty.filmswishlistservice.model.ExternalSeries
import ru.melowetty.filmswishlistservice.model.MovieType
import ru.melowetty.filmswishlistservice.repository.MovieRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService
import ru.melowetty.filmswishlistservice.service.ExternalMovieService
import ru.melowetty.filmswishlistservice.service.LocalizeService
import ru.melowetty.filmswishlistservice.service.MovieService

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val externalMovieService: ExternalMovieService,
    private val localizeService: LocalizeService,
    private val actorService: BaseLocalizedService<ActorEntity>,
    private val countryService: BaseLocalizedService<CountryEntity>,
    private val directorService: BaseLocalizedService<DirectorEntity>,
    private val genreService: BaseLocalizedService<GenreEntity>,
    private val languageService: BaseLocalizedService<LanguageEntity>,
    private val writerService: BaseLocalizedService<WriterEntity>
): MovieService {
    override fun searchMovie(query: String): List<MovieEntity> {
        val movies = externalMovieService.searchMovie(query)
        val existsMovies = movieRepository.findByImdbIdIn(movies.map { it.imdbId })
            .associateBy { it.imdbId }

        return movies.map {
            if (existsMovies.containsKey(it.imdbId)) {
                existsMovies.getValue(it.imdbId)
            }
            else {
                createMovie(it.imdbId)
            }
        }
    }

    private fun createMovie(imdbId: String): MovieEntity {
        val externalMovie = externalMovieService.getMovieByImdbId(imdbId)

        val title = localizeService.localize(externalMovie.title)
        val description = localizeService.localize(externalMovie.description)

        val actors = externalMovie.actors.map { actorService.getOrCreate(it) }.toMutableList()
        val countries = externalMovie.countries.map { countryService.getOrCreate(it) }.toMutableList()
        val directors = externalMovie.directors.map { directorService.getOrCreate(it) }.toMutableList()
        val genres = externalMovie.genres.map { genreService.getOrCreate(it) }.toMutableList()
        val languages = externalMovie.languages.map { languageService.getOrCreate(it) }.toMutableList()
        val writers = externalMovie.writers.map { writerService.getOrCreate(it) }.toMutableList()

        if (externalMovie.type == MovieType.FILM) {
            externalMovie as ExternalFilm

            val entity = FilmEntity(
                imdbId = imdbId,
                title = title,
                description = description,
                year = externalMovie.year,
                rating = externalMovie.rating,
                released = externalMovie.released,
                durationInMinutes = externalMovie.durationInMinutes,
                posterLink = externalMovie.posterLink,
                imdbRating = externalMovie.imdbRating,
                boxOffice = externalMovie.boxOffice,
                actors = actors,
                countries = countries,
                directors = directors,
                genres = genres,
                languages = languages,
                writers = writers,

            )

            return movieRepository.save(entity)
        } else {
            externalMovie as ExternalSeries

            val entity = SeriesEntity(
                imdbId = imdbId,
                title = title,
                description = description,
                year = externalMovie.year,
                rating = externalMovie.rating,
                released = externalMovie.released,
                durationInMinutes = externalMovie.durationInMinutes,
                posterLink = externalMovie.posterLink,
                imdbRating = externalMovie.imdbRating,
                actors = actors,
                countries = countries,
                directors = directors,
                genres = genres,
                languages = languages,
                writers = writers,
                lastYear = externalMovie.lastYear,
                seasonsCount = externalMovie.seasonsCount
            )

            return movieRepository.save(entity)
        }
    }

    override fun getMovieByImdbId(imdbId: String): MovieEntity {
        val entity = movieRepository.findByImdbId(imdbId)
            ?: createMovie(imdbId)

        return entity
    }

    override fun deleteMovieByImdbId(imdbId: String) {
        if (!movieRepository.existsByImdbId(imdbId)) {
            throw MovieNotFoundException("exception.movie-not-found.by-imdb")
        }

        movieRepository.deleteByImdbId(imdbId)
    }
}