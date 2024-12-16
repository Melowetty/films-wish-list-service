package ru.melowetty.filmswishlistservice.configuration

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MultiThreadingConfig {
    @Value("\${api.omdb.max-threads}")
    private val omdbMaxActiveThreadsCount = 0

    @Bean
    @Qualifier("omdb_semaphore")
    fun omdbSemaphore(): Semaphore {
        return Semaphore(omdbMaxActiveThreadsCount)
    }

    @Bean
    @Qualifier("fetch_data_threads")
    fun initCommandExecutors(): ExecutorService {
        return Executors.newFixedThreadPool(
            10
        )
    }

    @Bean
    @Qualifier("scheduled_movies_check_changes")
    fun moviesCheckChangesExecutor(): ExecutorService {
        return Executors.newFixedThreadPool(
            10
        )
    }
}