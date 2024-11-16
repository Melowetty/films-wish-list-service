package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseNamedEntity

@Entity
@Table(name = "actor")
class ActorEntity : BaseNamedEntity<Long>() {
    @ManyToMany(
        mappedBy = "actors",
        fetch = FetchType.LAZY
    )
    var movies: MutableSet<MovieEntity> = mutableSetOf()
}