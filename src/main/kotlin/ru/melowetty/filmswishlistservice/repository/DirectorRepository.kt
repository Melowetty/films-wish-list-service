package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.DirectorEntity

@Repository
interface DirectorRepository : JpaRepository<DirectorEntity, Long> {
    @Query("select d from DirectorEntity d where d.name.english = ?1 and d.name.russian = ?2")
    fun findByName_EnglishAndName_Russian(english: String, russian: String): DirectorEntity?
}