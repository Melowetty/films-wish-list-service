package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.GenreEntity

@Repository
interface GenreRepository : JpaRepository<GenreEntity, Long>