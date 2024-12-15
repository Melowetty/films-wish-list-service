package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.model.ExternalMovie

interface MovieService {
    fun searchMovie(query: String): List<MovieEntity>
    fun createMovie(externalMovie: ExternalMovie): MovieEntity
    fun getMovieByImdbId(imdbId: String): MovieEntity
    fun deleteMovieByImdbId(imdbId: String)
}