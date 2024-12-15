package ru.melowetty.filmswishlistservice.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.melowetty.filmswishlistservice.controller.request.MovieUserRateRequest
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.service.UserMovieService

@RestController
@RequestMapping("/movies")
class UserMovieController(
    private val userMovieService: UserMovieService
) {
    @GetMapping("/search")
    fun searchMovie(
        @RequestParam(name = "query")
        @NotBlank(message = "{movie.search.request.query.is-blank}")
        @Length(min = 2, max = 64, message = "{movie.search.request.query.bad-length}")
        query: String
    ): List<UserMovieShortDto> {
        return userMovieService.searchMovie(query)
    }

    @GetMapping("/list")
    fun getAllWishMovies(
        @RequestParam(name = "is_watched", required = false)
        isWatched: Boolean?
    ): List<UserMovieShortDto> {
        return userMovieService.getAllMovies(isWatched)
    }

    @GetMapping("/rating")
    fun getRatingOfAllWishMovies(): List<UserMovieShortDto> {
        return userMovieService.getRatingOfAllWishMovies()
    }

    @GetMapping("/{id}")
    fun getMovieById(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.getMovieByImdbId(imdbId)
    }

    @PostMapping("/{id}/wish")
    fun markMovieAsWish(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.addMovieToWishList(imdbId)
    }

    @DeleteMapping("/{id}/wish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun markMovieAsNotWish(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ) {
        userMovieService.removeMovieFromWishList(imdbId)
    }

    @PostMapping("/{id}/watched")
    fun markMovieAsWatched(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.markMovieAsWatched(imdbId)
    }

    @DeleteMapping("/{id}/watched")
    fun markMovieAsNotWatched(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.markMovieNotWatched(imdbId)
    }

    @PostMapping("/{id}/rate")
    fun rateMovie(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
        @Valid
        @RequestBody
        rateRequest: MovieUserRateRequest
    ): UserMovieDto {
        return userMovieService.rateMovie(imdbId, rateRequest.rate)
    }

    @DeleteMapping("/{id}/rate")
    fun removeMovieRate(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.removeMovieRate(imdbId)
    }
}