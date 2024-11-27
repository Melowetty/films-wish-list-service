package ru.melowetty.filmswishlistservice.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.melowetty.filmswishlistservice.dto.MovieDto
import ru.melowetty.filmswishlistservice.dto.MovieShortDto
import ru.melowetty.filmswishlistservice.service.MovieService

@RestController
@RequestMapping("/movies")
class MovieController(
    private val movieService: MovieService
) {
    @GetMapping("/search")
    fun searchMovie(
        @RequestParam(name = "query")
        @NotBlank(message = "{movie.search.request.query.is-blank}")
        @Length(min = 2, max = 64, message = "{movie.search.request.query.bad-length}")
        query: String
    ) : List<MovieShortDto> {
        return movieService.searchMovie(query)
    }

    @GetMapping("/{id}")
    fun getMovieById(
        @PathVariable(name = "id")
        @NotBlank(message = "{movie.detail-info.request.id.is-blank}")
        imdbId: String,
    ) : MovieDto {
        return movieService.getMovieByImdbId(imdbId)
    }
}