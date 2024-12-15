package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.CountryEntity

@Repository
interface CountryRepository : JpaRepository<CountryEntity, Long> {
    @Query("select c from CountryEntity c where c.name.english = ?1 and c.name.russian = ?2")
    fun findByName_EnglishAndName_Russian(english: String, russian: String): CountryEntity?
}
