package ru.melowetty.filmswishlistservice

import ru.melowetty.filmswishlistservice.entity.LocalizedEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData

class Extensions {
    companion object {
        fun LocalizedData.toLocalizedEntity(): LocalizedEntity {
            return LocalizedEntity(
                russian = this.russian,
                english = this.english
            )
        }
    }
}