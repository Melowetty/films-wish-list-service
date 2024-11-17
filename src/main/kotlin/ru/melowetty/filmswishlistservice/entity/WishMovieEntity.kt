package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity

@Entity
@Table(name = "wish_movie")
class WishMovieEntity : BaseEntity<Long>() {
    @ManyToOne
    lateinit var user: UserEntity

    @ManyToOne
    lateinit var movie: MovieEntity

    @Column(nullable = false)
    var isWatched: Boolean = false

    @Column
    var userRating: Int? = null
}