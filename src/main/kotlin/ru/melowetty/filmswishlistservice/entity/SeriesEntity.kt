package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class SeriesEntity : MovieEntity() {
    var seasonsCount: Int = 0
}