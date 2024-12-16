package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.GenreEntity

@Repository
interface GenreRepository : JpaRepository<GenreEntity, Long> {
    @Query("select g from GenreEntity g where g.name.english = ?1 and g.name.russian = ?2")
    fun findByName_EnglishAndName_Russian(english: String, russian: String): GenreEntity?
}