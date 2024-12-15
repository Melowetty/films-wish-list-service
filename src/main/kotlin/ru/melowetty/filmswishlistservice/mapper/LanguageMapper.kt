package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.dto.LanguageDto
import ru.melowetty.filmswishlistservice.entity.LanguageEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class LanguageMapper {
    fun toDto(lang: Language, entity: LanguageEntity): LanguageDto {
        return LanguageDto(
            id = entity.id!!,
            name = entity.name.getValueByLanguage(lang)
        )
    }
}