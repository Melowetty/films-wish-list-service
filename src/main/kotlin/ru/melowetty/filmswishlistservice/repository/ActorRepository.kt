package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.ActorEntity

@Repository
interface ActorRepository : JpaRepository<ActorEntity, Long> {

    @Query("select a from ActorEntity a where a.name.english = ?1 and a.name.russian = ?2")
    fun findByName_EnglishAndName_Russian(english: String, russian: String): ActorEntity?
}