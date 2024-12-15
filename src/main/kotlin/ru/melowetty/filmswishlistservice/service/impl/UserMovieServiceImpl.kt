package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity
import ru.melowetty.filmswishlistservice.mapper.UserMovieMapper
import ru.melowetty.filmswishlistservice.repository.WishMovieRepository
import ru.melowetty.filmswishlistservice.service.MovieService
import ru.melowetty.filmswishlistservice.service.UserMovieService
import ru.melowetty.filmswishlistservice.service.UserService

@Service
class UserMovieServiceImpl(
    private val movieService: MovieService,
    private val userService: UserService,
    private val userMovieRepository: WishMovieRepository,
    private val mapper: UserMovieMapper
) : UserMovieService {
    private val logging = KotlinLogging.logger { }

    override fun searchMovie(query: String): List<UserMovieShortDto> {
        val user = userService.getUserByAuth()
        val movies = movieService.searchMovie(query)

        val existsMovies = userMovieRepository
            .findByUser_IdAndMovie_ImdbIdIn(user.id!!, movies.map { it.imdbId }).associateBy { it.movie.imdbId }

        return movies.map {
            if (existsMovies.containsKey(it.imdbId)) {
                mapper.toShortDto(existsMovies[it.imdbId]!!)
            }
            else {
                mapper.movieToShortDto(it)
            }
        }
    }

    override fun getMovieByImdbId(imdbId: String): UserMovieDto {
        val user = userService.getUserByAuth()
        val userMovie = userMovieRepository.findByUser_IdAndMovie_ImdbId(user.id!!, imdbId)

        if (userMovie != null) {
            return mapper.toDto(userMovie)
        }

        val movie = movieService.getMovieByImdbId(imdbId)

        return mapper.movieToDto(movie)
    }

    private fun getMovieByImdbIdOrCreate(imdbId: String): WishMovieEntity {
        val user = userService.getUserByAuth()
        val userMovie = userMovieRepository.findByUser_IdAndMovie_ImdbId(user.id!!, imdbId)

        if (userMovie != null) {
            return userMovie
        }

        val movie = movieService.getMovieByImdbId(imdbId)

        val entity = WishMovieEntity(
            user,
            movie,
            isWatched = false,
            userRating = null,
        )

        return entity
    }

    override fun addMovieToWishList(imdbId: String): UserMovieDto {
        return mapper.toDto(getMovieByImdbIdOrCreate(imdbId))
    }

    override fun removeMovieFromWishList(imdbId: String) {
        val user = userService.getUserByAuth()
        val movie = movieService.getMovieByImdbId(imdbId)

        userMovieRepository.deleteByUserAndMovie(user, movie)
    }

    override fun markMovieAsWatched(imdbId: String): UserMovieDto {
        val movie = getMovieByImdbIdOrCreate(imdbId)
        movie.isWatched = true

        userMovieRepository.save(movie)
        return mapper.toDto(movie)
    }

    override fun markMovieNotWatched(imdbId: String): UserMovieDto {
        val movie = getMovieByImdbIdOrCreate(imdbId)
        movie.isWatched = false

        userMovieRepository.save(movie)
        return mapper.toDto(movie)
    }

    override fun rateMovie(imdbId: String, rate: Int): UserMovieDto {
        val movie = getMovieByImdbIdOrCreate(imdbId)
        movie.userRating = rate

        userMovieRepository.save(movie)
        return mapper.toDto(movie)
    }

    override fun removeMovieRate(imdbId: String): UserMovieDto {
        val movie = getMovieByImdbIdOrCreate(imdbId)
        movie.userRating = null

        userMovieRepository.save(movie)
        return mapper.toDto(movie)
    }
}