package ru.melowetty.filmswishlistservice.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieController {
    @GetMapping("/search")
    fun searchMovie(
        @RequestParam(name = "query")
        @NotBlank(message = "{movie.search.request.query.is-blank}")
        @Length(min = 2, max = 64, message = "{movie.search.request.query.bad-length}")
        query: String
    ) : String {
        return "test"
    }
}