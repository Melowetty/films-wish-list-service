package ru.melowetty.filmswishlistservice.mapper

import org.springframework.stereotype.Component
import ru.melowetty.filmswishlistservice.Extensions.Companion.getValueByLanguage
import ru.melowetty.filmswishlistservice.dto.WriterDto
import ru.melowetty.filmswishlistservice.entity.WriterEntity
import ru.melowetty.filmswishlistservice.model.Language

@Component
class WriterMapper {
    fun toDto(lang: Language, entity: WriterEntity): WriterDto {
        return WriterDto(
            id = entity.id!!,
            name = entity.name.getValueByLanguage(lang)
        )
    }
}