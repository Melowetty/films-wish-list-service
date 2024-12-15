package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.LanguageEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.LanguageRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService

@Service
class LanguageServiceImpl(
    private val languageRepository: LanguageRepository,
    private val localizeService: LocalizeServiceImpl
) : BaseLocalizedService<LanguageEntity> {
    override fun getOrCreate(localizedData: LocalizedData): LanguageEntity {
        val entity = languageRepository
            .findByName_EnglishAndName_Russian(localizedData.english, localizedData.russian)

        if (entity != null) {
            return entity
        }

        val language = LanguageEntity(
            name = localizeService.localize(localizedData)
        )

        return languageRepository.save(language)
    }
}