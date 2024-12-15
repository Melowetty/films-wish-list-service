package ru.melowetty.filmswishlistservice.mapper

import mu.KotlinLogging
import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity

@Component
class UserMovieMapper {
    fun toDto(entity: WishMovieEntity): UserMovieDto {
        TODO()
    }

    fun movieToDto(entity: MovieEntity): UserMovieDto {
TODO()
    }

    fun toShortDto(entity: WishMovieEntity): UserMovieShortDto {
        TODO()
    }

    fun movieToShortDto(entity: MovieEntity): UserMovieShortDto {
TODO()
    }
}