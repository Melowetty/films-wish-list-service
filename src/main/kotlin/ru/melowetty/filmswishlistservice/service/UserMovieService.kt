package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto

interface UserMovieService {
    fun searchMovie(query: String): List<UserMovieShortDto>
    fun getAllMovies(isWatched: Boolean?): List<UserMovieShortDto>
    fun getRatingOfAllWishMovies(): List<UserMovieShortDto>
    fun getMovieByImdbId(imdbId: String): UserMovieDto
    fun addMovieToWishList(imdbId: String): UserMovieDto
    fun removeMovieFromWishList(imdbId: String)
    fun markMovieAsWatched(imdbId: String): UserMovieDto
    fun markMovieNotWatched(imdbId: String): UserMovieDto
    fun rateMovie(imdbId: String, rate: Int): UserMovieDto
    fun removeMovieRate(imdbId: String): UserMovieDto
}