package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity


@Entity
@Table(name = "role")
class RoleEntity : GrantedAuthority, BaseEntity<Long>() {
    @Column(nullable = false, unique = true)
    private lateinit var name: String

    @ManyToMany(mappedBy = "roles")
    val users: MutableSet<UserEntity> = mutableSetOf()

    override fun getAuthority(): String {
        return name
    }
}