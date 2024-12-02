package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
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

    override fun searchMovie(query: String): List<ExternalShortMovie> {
        logger.info { "Получение данных из кэша" }
        return externalMovieService.searchMovie(query)
    }

    override fun getMovieByImdbId(imdbId: String): ExternalMovie {
        logger.info { "Получение данных из кэша" }
        return externalMovieService.getMovieByImdbId(imdbId)
    }
}