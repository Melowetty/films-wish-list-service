package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.CountryEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.CountryRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService
import ru.melowetty.filmswishlistservice.service.LocalizeService

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
    private val localizeService: LocalizeService
) : BaseLocalizedService<CountryEntity> {
    override fun getOrCreate(localizedData: LocalizedData): CountryEntity {
        val entity = countryRepository
            .findByName_EnglishAndName_Russian(localizedData.english, localizedData.russian)

        if (entity != null) {
            return entity
        }

        val country = CountryEntity(
            name = localizeService.localize(localizedData)
        )

        return countryRepository.save(country)
    }
}