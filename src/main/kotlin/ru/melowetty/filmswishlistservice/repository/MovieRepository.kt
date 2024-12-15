package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.MovieEntity

@Repository
interface MovieRepository : JpaRepository<MovieEntity, Long> {
    fun findByImdbIdIn(imdbIds: Collection<String>): List<MovieEntity>

    fun findByImdbId(imdbId: String): MovieEntity?

    fun existsByImdbId(imdbId: String): Boolean

    fun deleteByImdbId(imdbId: String)
}