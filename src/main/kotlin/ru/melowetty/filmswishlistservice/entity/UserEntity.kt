package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.melowetty.filmswishlistservice.entity.base.BaseAuditEntity
import ru.melowetty.filmswishlistservice.model.Provider

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

    @Enumerated(value = EnumType.STRING)
    lateinit var provider: Provider

    @Column
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private val wishMovies: MutableList<WishMovieEntity> = mutableListOf()

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