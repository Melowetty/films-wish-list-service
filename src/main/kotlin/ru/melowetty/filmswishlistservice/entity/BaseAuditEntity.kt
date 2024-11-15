package ru.melowetty.filmswishlistservice.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAuditEntity<T> : BaseEntity<T>() {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    lateinit var created: LocalDateTime

    @LastModifiedDate
    @Column(nullable = false)
    lateinit var modified: LocalDateTime
}