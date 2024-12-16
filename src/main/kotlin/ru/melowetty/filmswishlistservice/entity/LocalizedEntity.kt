package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.melowetty.filmswishlistservice.entity.base.BaseEntity

@Entity
@Table(name = "translate")
class LocalizedEntity(
    @Column(columnDefinition = "TEXT")
    val english: String,

    @Column(columnDefinition = "TEXT")
    val russian: String,
): BaseEntity<Long>()