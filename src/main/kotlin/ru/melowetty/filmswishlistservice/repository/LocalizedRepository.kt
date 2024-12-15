package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.LocalizedEntity

@Repository
interface LocalizedRepository : JpaRepository<LocalizedEntity, Long>