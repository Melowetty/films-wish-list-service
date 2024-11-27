package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto

interface UserMovieService {
    fun searchMovie(query: String): List<UserMovieShortDto>
    fun getMovieByImdbId(imdbId: String): UserMovieDto
    fun addMovieToWishList(imdbId: String)
    fun removeMovieFromWishList(imdbId: String)
    fun markMovieAsWatched(imdbId: String)
    fun markMovieNotWatched(imdbId: String)
    fun rateMovie(imdbId: String, rate: Float)
    fun removeMovieRate(imdbId: String)
}