package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.dto.UserMovieDto
import ru.melowetty.filmswishlistservice.dto.UserMovieShortDto
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity

@Component
class UserMovieMapper {
    fun toDto(entity: WishMovieEntity): UserMovieDto {

    }

    fun movieToDto(entity: MovieEntity): UserMovieDto {

    }

    fun toShortDto(entity: WishMovieEntity): UserMovieShortDto {

    }

    fun movieToShortDto(entity: MovieEntity): UserMovieShortDto {

    }
}