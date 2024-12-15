package ru.melowetty.filmswishlistservice

import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.entity.LocalizedEntity
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.model.Rating

class Extensions {
    companion object {
        fun LocalizedData.toLocalizedEntity(): LocalizedEntity {
            return LocalizedEntity(
                russian = this.russian,
                english = this.english
            )
        }

        fun LocalizedEntity.getValueByLanguage(lang: Language): String {
            return if (lang == Language.RUSSIAN) {
                this.russian
            } else {
                this.english
            }
        }

        fun Rating.toLocalRatingSystem(lang: Language): Rating {
            return if (lang == Language.RUSSIAN) {
                this.toRussianRating()
            } else {
                this.toEnglishRating()
            }
        }
    }
}