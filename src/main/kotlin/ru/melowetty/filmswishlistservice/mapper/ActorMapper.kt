package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.dto.ActorDto
import ru.melowetty.filmswishlistservice.entity.ActorEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class ActorMapper {
    fun toDto(lang: Language, entity: ActorEntity): ActorDto {
        return ActorDto(
            id = entity.id!!,
            name = entity.name.getValueByLanguage(lang)
        )
    }
}