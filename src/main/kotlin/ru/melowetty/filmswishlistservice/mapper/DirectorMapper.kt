package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.dto.DirectorDto
import ru.melowetty.filmswishlistservice.entity.DirectorEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class DirectorMapper {
    fun toDto(lang: Language, entity: DirectorEntity): DirectorDto {
        return DirectorDto(
            id = entity.id!!,
            name = entity.name.getValueByLanguage(lang)
        )
    }
}