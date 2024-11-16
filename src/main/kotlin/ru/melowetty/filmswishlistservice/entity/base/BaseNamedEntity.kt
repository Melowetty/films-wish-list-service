package ru.melowetty.filmswishlistservice.entity.base

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseNamedEntity<T> : BaseEntity<T>() {
    @Column(nullable = false)
    lateinit var name: String
}