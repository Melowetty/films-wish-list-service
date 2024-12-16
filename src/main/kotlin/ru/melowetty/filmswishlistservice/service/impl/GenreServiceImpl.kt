package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.GenreEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.GenreRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService

@Service
class GenreServiceImpl(
    private val genreRepository: GenreRepository,
    private val localizeService: LocalizeServiceImpl
) : BaseLocalizedService<GenreEntity> {
    override fun getOrCreate(localizedData: LocalizedData): GenreEntity {
        val entity = genreRepository
            .findByName_EnglishAndName_Russian(localizedData.english, localizedData.russian)

        if (entity != null) {
            return entity
        }

        val genre = GenreEntity(
            name = localizeService.localize(localizedData)
        )

        return genreRepository.save(genre)
    }
}