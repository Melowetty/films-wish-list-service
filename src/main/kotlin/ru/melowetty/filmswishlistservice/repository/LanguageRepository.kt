package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.LanguageEntity

@Repository
interface LanguageRepository : JpaRepository<LanguageEntity, Long> {
    @Query("select l from LanguageEntity l where l.name.english = ?1 and l.name.russian = ?2")
    fun findByName_EnglishAndName_Russian(english: String, russian: String): LanguageEntity?
}