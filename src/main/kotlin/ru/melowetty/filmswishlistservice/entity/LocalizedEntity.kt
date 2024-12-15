package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity

@Entity
@Table(name = "translate")
class LocalizedEntity(
    @Column(length = 512, nullable = false)
    val english: String,

    @Column(length = 512, nullable = false)
    val russian: String,
): BaseEntity<Long>()