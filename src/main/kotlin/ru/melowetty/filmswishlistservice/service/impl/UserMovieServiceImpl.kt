package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.service.ExternalMovieService
import ru.melowetty.filmswishlistservice.service.UserMovieService

@Service
class UserMovieServiceImpl(
    private val externalMovieService: ExternalMovieService
) : UserMovieService {
    private val logging = KotlinLogging.logger { }

    override fun searchMovie(query: String): List<UserMovieShortDto> {
        logging.info { externalMovieService.searchMovie(query) }
        TODO("Not yet implemented")
    }

    override fun getMovieByImdbId(imdbId: String): UserMovieDto {
        TODO("Not yet implemented")
    }

    override fun addMovieToWishList(imdbId: String) {
        TODO("Not yet implemented")
    }

    override fun removeMovieFromWishList(imdbId: String) {
        TODO("Not yet implemented")
    }

    override fun markMovieAsWatched(imdbId: String) {
        TODO("Not yet implemented")
    }

    override fun markMovieNotWatched(imdbId: String) {
        TODO("Not yet implemented")
    }

    override fun rateMovie(imdbId: String, rate: Float) {
        TODO("Not yet implemented")
    }

    override fun removeMovieRate(imdbId: String) {
        TODO("Not yet implemented")
    }
}