package ru.melowetty.filmswishlistservice.scheduler

import java.time.LocalDate
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.MovieEntity_
import ru.melowetty.filmswishlistservice.entity.SeriesEntity
import ru.melowetty.filmswishlistservice.model.ExternalFilm
import ru.melowetty.filmswishlistservice.model.ExternalSeries
import ru.melowetty.filmswishlistservice.model.MovieType
import ru.melowetty.filmswishlistservice.repository.MovieRepository
import ru.melowetty.filmswishlistservice.service.ExternalMovieService

@Component
class MoviesChangesCheckScheduler(
    private val movieRepository: MovieRepository,
    private val externalMovieService: ExternalMovieService,
    @Qualifier("scheduled_movies_check_changes")
    private val executorService: ExecutorService
) {
    private val logger = KotlinLogging.logger {  }

    @Scheduled(cron = "0 0 3 * * 6")
    fun checkMoviesChanges() {
        logger.info { "Начата работа проверки фильмов на изменения" }

        val dateForUpdate = LocalDate.now().minusDays(7)

        val isNotChangedSpecification = Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get(MovieEntity_.isChanged), false)
        }

        val isMustBeUpdated = Specification { root, query, criteriaBuilder ->
            criteriaBuilder.lessThanOrEqualTo(root.get(MovieEntity_.lastCheck), dateForUpdate)
        }

        val specification = Specification.allOf(isNotChangedSpecification, isMustBeUpdated)

        var slice = movieRepository.findAll(specification, PageRequest.of(1, 30))

        var changedMovies = 0

        while (!slice.isEmpty) {
            val movies = slice.get()

            val tasks = executorService.invokeAll(movies.map { Callable {
                    movieCheckChanges(it)
                }
            }.toList())

            changedMovies += tasks.map { it.get() }.filter { it }.size

            slice = movieRepository.findAll(specification, slice.nextPageable())
        }

        logger.info { "Закончена работа проверки фильмов на изменения, всего изменено $changedMovies" }
    }

    fun movieCheckChanges(entity: MovieEntity): Boolean {
        val actualMovie = externalMovieService.getMovieByImdbId(imdbId = entity.imdbId)
        val currentMovie = entity

        currentMovie.lastCheck = LocalDate.now()

        if (actualMovie.type == MovieType.FILM) {
            actualMovie as ExternalFilm

            if (actualMovie.released != currentMovie.released) {
                currentMovie.isChanged = true
                currentMovie.released = actualMovie.released
            }
        }
        else {
            actualMovie as ExternalSeries
            currentMovie as SeriesEntity

            if (actualMovie.lastYear != currentMovie.lastYear) {
                currentMovie.isChanged = true
                currentMovie.lastYear = actualMovie.lastYear
            }
        }

        movieRepository.save(currentMovie)

        return currentMovie.isChanged
    }
}