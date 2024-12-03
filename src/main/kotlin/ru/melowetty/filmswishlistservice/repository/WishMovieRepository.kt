package ru.melowetty.filmswishlistservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.WishMovieEntity

@Repository
interface WishMovieRepository : JpaRepository<WishMovieEntity, Long>