package ru.melowetty.filmswishlistservice.entity.base

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseNamedEntity<T>(
    @Column(nullable = false)
    var name: String
) : BaseEntity<T>()