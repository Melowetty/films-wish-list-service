package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity
import ru.melowetty.filmswishlistservice.entity.base.BaseNamedEntity

@Entity
@Table(name = "language")
class LanguageEntity(
    @ManyToOne
    val name: LocalizedEntity,

    @ManyToMany(
        mappedBy = "languages",
        fetch = FetchType.LAZY
    )
    val movies: MutableSet<MovieEntity> = mutableSetOf()
) : BaseEntity<Long>()