package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import org.hibernate.annotations.NaturalId
import ru.melowetty.filmswishlistservice.entity.base.BaseAuditEntity
import ru.melowetty.filmswishlistservice.model.Rating

@Entity
@Table(name = "movie")
class MovieEntity(
    @Column(nullable = false)
    @NaturalId
    val imdbId: String,

    @ManyToOne
    var title: LocalizedEntity,

    @Column(nullable = false)
    val year: Int,

    @Column
    @Enumerated(value = EnumType.STRING)
    var rating: Rating?,

    @Column(nullable = false)
    var released: LocalDate,

    @ManyToMany
    val genres: MutableList<GenreEntity> = mutableListOf(),

    @ManyToMany
    val countries: MutableList<CountryEntity> = mutableListOf(),

    @ManyToMany
    val directors: MutableList<DirectorEntity> = mutableListOf(),

    @ManyToMany
    val writers: MutableList<WriterEntity> = mutableListOf(),

    @ManyToMany
    val actors: MutableList<ActorEntity> = mutableListOf(),

    @ManyToMany
    val languages: MutableList<LanguageEntity> = mutableListOf(),

    @Column(name = "duration")
    var durationInMinutes: Int?,

    @ManyToOne
    var description: LocalizedEntity?,

    @Column
    var posterLink: String?,

    @Column(columnDefinition = "NUMERIC(2, 1)")
    var imdbRating: Float?,
) : BaseAuditEntity<Long>() {

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
