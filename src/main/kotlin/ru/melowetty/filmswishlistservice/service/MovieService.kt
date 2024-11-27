package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.dto.MovieDto
import ru.melowetty.filmswishlistservice.dto.MovieShortDto

interface MovieService {
    fun searchMovie(query: String): List<MovieShortDto>
    fun getMovieByImdbId(imdbId: String): MovieDto
}