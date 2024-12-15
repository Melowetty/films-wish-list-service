package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.MovieEntity
import ru.melowetty.filmswishlistservice.entity.UserEntity
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity

@Repository
interface WishMovieRepository : JpaRepository<WishMovieEntity, Long>, JpaSpecificationExecutor<WishMovieEntity> {


    fun findByUser_IdAndMovie_ImdbIdIn(id: Long, imdbIds: Collection<String>): List<WishMovieEntity>

    fun deleteByUser_IdAndMovie_ImdbId(userId: Long, imdbId: String)

    fun findByUser_IdAndMovie_ImdbId(id: Long, imdbId: String): WishMovieEntity?

    fun findByUserId(userId: Long): List<WishMovieEntity>

}