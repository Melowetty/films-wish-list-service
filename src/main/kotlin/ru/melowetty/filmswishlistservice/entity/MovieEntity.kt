package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.hibernate.annotations.NaturalId
import ru.melowetty.filmswishlistservice.entity.base.BaseAuditEntity
import ru.melowetty.filmswishlistservice.model.Rating

@Entity
@Table(name = "movie")
class MovieEntity : BaseAuditEntity<Long>() {

    @Column(nullable = false)
    @NaturalId
    lateinit var imdbId: String

    @Column(nullable = false)
    lateinit var title: String

    @Column(nullable = false)
    var year: Int = 0

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    lateinit var rating: Rating

    @Column(nullable = false)
    lateinit var released: LocalDateTime

    @ManyToMany
    val genres: MutableSet<GenreEntity> = mutableSetOf()

    @ManyToMany
    val countries: MutableSet<CountryEntity> = mutableSetOf()

    @ManyToMany
    val directors: MutableList<DirectorEntity> = mutableListOf()

    @ManyToMany
    val writers: MutableList<WriterEntity> = mutableListOf()

    @ManyToMany
    val actors: MutableList<ActorEntity> = mutableListOf()

    @ManyToMany
    val languages: MutableSet<LanguageEntity> = mutableSetOf()

    @Column(name = "duration", nullable = false)
    var durationInMinutes: Int = 0

    @Column(nullable = false)
    lateinit var description: String

    @Column(nullable = false)
    lateinit var posterLink: String

    @Column(nullable = false)
    var imdbRating: Float = 0f

    override fun hashCode(): Int {
        return imdbId.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as MovieEntity

        return imdbId == other.imdbId
    }
}
