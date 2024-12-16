package ru.melowetty.filmswishlistservice.repository

import java.time.LocalDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.MovieEntity

@Repository
interface MovieRepository : JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {
    fun findByImdbIdIn(imdbIds: Collection<String>): List<MovieEntity>

    fun findByImdbId(imdbId: String): MovieEntity?

    fun existsByImdbId(imdbId: String): Boolean

    fun deleteByImdbId(imdbId: String)

    @Query("select m.id from MovieEntity m where m.released = ?1")
    fun getAllIdsWhichReleasedToday(released: LocalDate): List<Long>
}