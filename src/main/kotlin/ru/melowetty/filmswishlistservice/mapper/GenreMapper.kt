package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.dto.GenreDto
import ru.melowetty.filmswishlistservice.entity.GenreEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class GenreMapper {
    fun toDto(lang: Language, entity: GenreEntity): GenreDto {
        return GenreDto(
            id = entity.id!!,
            name = entity.name.getValueByLanguage(lang)
        )
    }
}