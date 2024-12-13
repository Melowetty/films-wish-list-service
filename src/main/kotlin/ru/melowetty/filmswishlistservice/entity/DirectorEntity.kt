package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseNamedEntity

@Entity
@Table(name = "director")
class DirectorEntity(
    name: String,

    @ManyToMany(
        mappedBy = "directors",
        fetch = FetchType.LAZY
    )
    val movies: MutableSet<MovieEntity> = mutableSetOf()
) : BaseNamedEntity<Long>(name)