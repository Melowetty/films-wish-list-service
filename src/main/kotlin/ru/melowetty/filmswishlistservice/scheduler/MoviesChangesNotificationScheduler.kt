package ru.melowetty.filmswishlistservice.scheduler

import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.entity.FilmEntity
import ru.melowetty.filmswishlistservice.entity.SeriesEntity
import ru.melowetty.filmswishlistservice.entity.UserEntity
import ru.melowetty.filmswishlistservice.mapper.UserMapper
import ru.melowetty.filmswishlistservice.notification.model.MovieChanges
import ru.melowetty.filmswishlistservice.notification.model.MoviesChangesNotification
import ru.melowetty.filmswishlistservice.notification.model.SeriesChanges
import ru.melowetty.filmswishlistservice.repository.MovieRepository
import ru.melowetty.filmswishlistservice.repository.UserRepository
import ru.melowetty.filmswishlistservice.service.NotificationService

@Component
class MoviesChangesNotificationScheduler(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val notificationService: NotificationService,
    private val userMapper: UserMapper,
) {
    private val logger = KotlinLogging.logger {  }

    @Scheduled(cron = "0 0 12 * * *")
    @Transactional
    fun checkMoviesChanges() {
        logger.info { "Начата проверка на изменения в фильмах, которые есть у человека в виш листе" }

        val movies = movieRepository.getAllIdsWhichChanged().toHashSet()

        var slice = userRepository.findAll(PageRequest.of(0, 500))

        var notifyForUsers: Long = 0

        while (!slice.isLast) {
            val users = slice.get()

            notifyForUsers += users.map { processUser(it, movies) }.filter { it }.count()

            slice = userRepository.findAll(slice.nextOrLastPageable())
        }

        val users = slice.get()

        notifyForUsers += users.map { processUser(it, movies) }.filter { it }.count()

        val moviesEntity = movieRepository.getAllMoviesWhichChanged().map {
            it.isChanged = false
            it
        }

        movieRepository.saveAll(moviesEntity)

        logger.info { "Закончена проверка на изменения в фильмах, которые есть у человека в виш листе, " +
                "разослано $notifyForUsers уведомлений" }
    }

    fun processUser(user: UserEntity, movies: Set<Long>): Boolean {
        val changedMovies = user.wishMovies.map { it.movie }.filter { movies.contains(it.id!!) }

        if (changedMovies.isEmpty()) return false

        val lang = user.language

        val seriesChanged = changedMovies.filter { it is SeriesEntity }.map {
            it as SeriesEntity
            SeriesChanges(
                title = it.title.getValueByLanguage(lang),
                lastYear = it.lastYear
            )
        }

        val filmChanged = changedMovies.filter { it is FilmEntity }.map {
            it as FilmEntity
            MovieChanges(
                movieTitle = it.title.getValueByLanguage(lang),
                newDate = it.released
            )
        }

        val notification = MoviesChangesNotification(
            seriesChanges = seriesChanged,
            movieChanges = filmChanged
        )

        notificationService.notify(userMapper.toInfo(user), notification)

        return true
    }
}