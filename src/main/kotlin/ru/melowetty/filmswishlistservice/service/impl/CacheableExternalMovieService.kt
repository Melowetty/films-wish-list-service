package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.model.ExternalMovie
import ru.melowetty.filmswishlistservice.model.ExternalShortMovie
import ru.melowetty.filmswishlistservice.service.ExternalMovieService

@Primary
@Service
class CacheableExternalMovieService(
    private val externalMovieService: ExternalMovieService
) : ExternalMovieService {
    private val logger = KotlinLogging.logger {  }

    @Cacheable("responses")
    override fun searchMovie(query: String): List<ExternalShortMovie> {
        logger.info { "Получение реальных данных о фильмах из внешнего источника" }
        return externalMovieService.searchMovie(query)
    }

    @Cacheable("movies")
    override fun getMovieByImdbId(imdbId: String): ExternalMovie {
        logger.info { "Получение реальных данных о фильме из внешнего источника" }
        return externalMovieService.getMovieByImdbId(imdbId)
    }
}