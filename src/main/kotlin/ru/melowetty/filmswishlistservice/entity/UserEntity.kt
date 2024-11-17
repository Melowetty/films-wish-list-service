package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.melowetty.filmswishlistservice.entity.base.BaseAuditEntity

@Entity
@Table(name = "users")
class UserEntity : UserDetails, BaseAuditEntity<Long>() {
    @Column(unique = true, nullable = false)
    private lateinit var username: String

    @Column(nullable = false)
    private lateinit var password: String

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    val roles: MutableSet<RoleEntity> = mutableSetOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

}