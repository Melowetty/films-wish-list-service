package ru.melowetty.filmswishlistservice.repository

import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.melowetty.filmswishlistservice.entity.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    fun findByUsername(username: String): Optional<UserEntity>
}