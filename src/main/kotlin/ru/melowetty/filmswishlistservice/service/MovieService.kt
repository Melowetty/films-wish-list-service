package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.entity.MovieEntity

interface MovieService {
    fun searchMovie(query: String): List<MovieEntity>
    fun getMovieByImdbId(imdbId: String): MovieEntity
    fun deleteMovieByImdbId(imdbId: String)
}