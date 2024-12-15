package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.Extensions.Companion.toLocalizedEntity
import ru.melowetty.filmswishlistservice.entity.LocalizedEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.LocalizedRepository
import ru.melowetty.filmswishlistservice.service.LocalizeService

@Service
class LocalizeServiceImpl(
    private val localizedRepository: LocalizedRepository
) : LocalizeService {
    override fun localize(data: LocalizedData): LocalizedEntity {
        return localizedRepository.save(data.toLocalizedEntity())
    }

}