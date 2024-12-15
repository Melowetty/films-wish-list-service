package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.dto.CountryDto
import ru.melowetty.filmswishlistservice.dto.GenreDto
import ru.melowetty.filmswishlistservice.entity.CountryEntity
import ru.melowetty.filmswishlistservice.entity.GenreEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class CountryMapper {
    fun toDto(lang: Language, entity: CountryEntity): CountryDto {
        return CountryDto(
            id = entity.id!!,
            name = entity.name.getValueByLanguage(lang)
        )
    }
}