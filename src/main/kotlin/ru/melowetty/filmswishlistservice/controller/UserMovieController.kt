package ru.melowetty.filmswishlistservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Работа с фильмами")
class UserMovieController(
    private val userMovieService: UserMovieService
) {
    @Operation(description = "Найти фильм по ключевому слову или фразе")
    @GetMapping("/search")
    fun searchMovie(
        @Parameter(name = "Запрос", example = "Avengers")
        @RequestParam(name = "query")
        @NotBlank(message = "{movie.search.request.query.is-blank}")
        @Length(min = 2, max = 64, message = "{movie.search.request.query.bad-length}")
        query: String
    ): List<UserMovieShortDto> {
        return userMovieService.searchMovie(query)
    }

    @Operation(description = "Получить список фильмов из виш листа")
    @GetMapping("/list")
    fun getAllWishMovies(
        @Parameter(name = "Отметка о просмотре")
        @RequestParam(name = "is_watched", required = false)
        isWatched: Boolean?
    ): List<UserMovieShortDto> {
        return userMovieService.getAllMovies(isWatched)
    }

    @Operation(description = "Получить личный топ фильмов, у которых стоит оценка")
    @GetMapping("/rating")
    fun getRatingOfAllWishMovies(): List<UserMovieShortDto> {
        return userMovieService.getRatingOfAllWishMovies()
    }

    @Operation(description = "Получить подробную информацию о фильме")
    @GetMapping("/{id}")
    fun getMovieById(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.getMovieByImdbId(imdbId)
    }

    @Operation(description = "Добавить фильм в виш лист")
    @PostMapping("/{id}/wish")
    fun markMovieAsWish(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.addMovieToWishList(imdbId)
    }

    @Operation(description = "Удалить фильм из виш листа")
    @DeleteMapping("/{id}/wish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun markMovieAsNotWish(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ) {
        userMovieService.removeMovieFromWishList(imdbId)
    }

    @Operation(description = "Отметить фильм как просмотренный")
    @PostMapping("/{id}/watched")
    fun markMovieAsWatched(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.markMovieAsWatched(imdbId)
    }

    @Operation(description = "Убрать отметку, что фильм просмотренный")
    @DeleteMapping("/{id}/watched")
    fun markMovieAsNotWatched(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.markMovieNotWatched(imdbId)
    }

    @Operation(description = "Поставить оценку фильму")
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

    @Operation(description = "Убрать оценку фильму")
    @DeleteMapping("/{id}/rate")
    fun removeMovieRate(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ): UserMovieDto {
        return userMovieService.removeMovieRate(imdbId)
    }
}