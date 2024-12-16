package ru.melowetty.filmswishlistservice.scheduler

import java.time.LocalDate
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.entity.UserEntity
import ru.melowetty.filmswishlistservice.mapper.UserMapper
import ru.melowetty.filmswishlistservice.notification.model.MovieIsReleasedNotification
import ru.melowetty.filmswishlistservice.repository.MovieRepository
import ru.melowetty.filmswishlistservice.repository.UserRepository
import ru.melowetty.filmswishlistservice.service.NotificationService

@Component
class MovieIsReleasedCheckScheduler(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val notificationService: NotificationService,
    private val userMapper: UserMapper,
) {
    private val logger = KotlinLogging.logger {  }

    @Scheduled(cron = "0 0 10 * * *")
    fun getMovieWhichTodayIsReleased() {
        logger.info { "Начата проверка вышел ли фильм, который есть у человека в виш листе" }

        val today = LocalDate.now()

        val movies = movieRepository.getAllIdsWhichReleasedToday(today).toHashSet()

        var slice = userRepository.findAll(PageRequest.of(1, 500))

        var notifyForUsers: Long = 0

        while (!slice.isEmpty) {
            val users = slice.get()

            notifyForUsers += users.map { processUser(it, today, movies) }.filter { it }.count()

            slice = userRepository.findAll(slice.nextPageable())
        }

        logger.info { "Закончена проверка вышел ли фильм, который есть у человека в виш листе, " +
                "разослано $notifyForUsers уведомлений" }
    }

    fun processUser(user: UserEntity, currentDate: LocalDate, movies: Set<Long>): Boolean {
        val releasedMovies = user.wishMovies.map { it.movie }.filter { movies.contains(it.id!!) }

        if (releasedMovies.isEmpty()) return false

        val notification = MovieIsReleasedNotification(
            titles = releasedMovies.map { it.title.getValueByLanguage(user.language) }
        )

        notificationService.notify(userMapper.toInfo(user), notification)

        return true
    }
}