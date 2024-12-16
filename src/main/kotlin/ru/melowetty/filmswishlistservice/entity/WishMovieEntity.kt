package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity

@Entity
@Table(name = "wish_movie")
class WishMovieEntity(
    @ManyToOne
    val user: UserEntity,

    @ManyToOne
    val movie: MovieEntity,

    @Column(nullable = false)
    var isWatched: Boolean,

    @Column
    var userRating: Int?,

    @Column(updatable = false, nullable = false)
    var created: LocalDateTime = LocalDateTime.now()
) : BaseEntity<Long>()