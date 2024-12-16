package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.model.ExternalMovie
import ru.melowetty.filmswishlistservice.model.ExternalShortMovie

interface ExternalMovieService {
    fun searchMovie(query: String): List<ExternalShortMovie>
    fun getMovieByImdbId(imdbId: String): ExternalMovie
}