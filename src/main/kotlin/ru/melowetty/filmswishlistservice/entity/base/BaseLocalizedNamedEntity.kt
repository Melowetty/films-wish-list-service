package ru.melowetty.filmswishlistservice.entity.base

import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import ru.melowetty.filmswishlistservice.entity.LocalizedEntity

@MappedSuperclass
abstract class BaseLocalizedNamedEntity<T>(
    @ManyToOne
    val name: LocalizedEntity,
) : BaseEntity<T>()