package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.entity.UserEntity_
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity_
import ru.melowetty.filmswishlistservice.exception.MovieNotFoundException
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
                mapper.movieToShortDto(user.language, it)
            }
        }
    }

    override fun getAllMovies(isWatched: Boolean?): List<UserMovieShortDto> {
        val user = userService.getUserByAuth()
        val specifications = mutableListOf<Specification<WishMovieEntity>>()

        specifications.add(Specification { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get(WishMovieEntity_.user).get(UserEntity_.id), user.id!!)
        })

        if (isWatched != null) {
            specifications.add(Specification { root, query, criteriaBuilder ->
                criteriaBuilder.equal(root.get(WishMovieEntity_.isWatched), isWatched)
            })
        }

        return userMovieRepository.findAll(Specification.allOf(specifications))
            .sortedByDescending { it.created }
            .map {
                mapper.toShortDto(it)
        }
    }

    override fun getRatingOfAllWishMovies(): List<UserMovieShortDto> {
        val user = userService.getUserByAuth()

        return userMovieRepository.findByUserId(user.id!!)
            .filter { it.userRating != null }
            .sortedByDescending { it.userRating }
            .map {
                mapper.toShortDto(it)
        }
    }

    override fun getMovieByImdbId(imdbId: String): UserMovieDto {
        val user = userService.getUserByAuth()
        val userMovie = userMovieRepository.findByUser_IdAndMovie_ImdbId(user.id!!, imdbId)

        if (userMovie != null) {
            return mapper.toDto(userMovie)
        }

        val movie = movieService.getMovieByImdbId(imdbId)

        return mapper.movieToDto(user.language, movie)
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

        userMovieRepository.save(entity)

        return entity
    }

    override fun addMovieToWishList(imdbId: String): UserMovieDto {
        return mapper.toDto(getMovieByImdbIdOrCreate(imdbId))
    }

    override fun removeMovieFromWishList(imdbId: String) {
        val user = userService.getUserByAuth()

        val entity = userMovieRepository.findByUser_IdAndMovie_ImdbId(user.id!!, imdbId)
            ?: throw MovieNotFoundException("exception.wish-movie-not-found.by-imdb")

        userMovieRepository.delete(entity)
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