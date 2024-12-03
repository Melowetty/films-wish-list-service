package ru.melowetty.filmswishlistservice.repository

import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): Optional<UserEntity>
}