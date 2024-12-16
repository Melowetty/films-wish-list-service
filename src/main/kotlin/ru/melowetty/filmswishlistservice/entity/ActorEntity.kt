package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseLocalizedNamedEntity

@Entity
@Table(name = "actor")
class ActorEntity(
    name: LocalizedEntity,

    @ManyToMany(
        mappedBy = "actors",
        fetch = FetchType.LAZY
    )
    val movies: MutableSet<MovieEntity> = mutableSetOf()
) : BaseLocalizedNamedEntity<Long>(name)