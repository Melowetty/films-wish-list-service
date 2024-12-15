package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity
import ru.melowetty.filmswishlistservice.entity.base.BaseNamedEntity

@Entity
@Table(name = "genre")
class GenreEntity(
    @ManyToOne
    val name: LocalizedEntity,

    @ManyToMany(
        mappedBy = "genres",
        fetch = FetchType.LAZY
    )
    val movies: MutableSet<MovieEntity> = mutableSetOf()
) : BaseEntity<Long>()