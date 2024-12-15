package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.UserEntity
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity

@Repository
interface WishMovieRepository : JpaRepository<WishMovieEntity, Long> {


    fun findByUser_IdAndMovie_ImdbIdIn(id: Long, imdbIds: Collection<String>): List<WishMovieEntity>

    fun deleteByUserAndMovie(user: UserEntity, movie: MovieEntity)

    fun findByUser_IdAndMovie_ImdbId(id: Long, imdbId: String): WishMovieEntity?

}