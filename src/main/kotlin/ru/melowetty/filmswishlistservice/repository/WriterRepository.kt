package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.WriterEntity

@Repository
interface WriterRepository : JpaRepository<WriterEntity, Long> {
    @Query("select w from WriterEntity w where w.name.english = ?1 and w.name.russian = ?2")
    fun findByName_EnglishAndName_Russian(english: String, russian: String): WriterEntity?
}