package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.DirectorEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.DirectorRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService

@Service
class DirectorServiceImpl(
    private val directorRepository: DirectorRepository,
    private val localizeService: LocalizeServiceImpl
) : BaseLocalizedService<DirectorEntity> {
    override fun getOrCreate(localizedData: LocalizedData): DirectorEntity {
        val entity = directorRepository
            .findByName_EnglishAndName_Russian(localizedData.english, localizedData.russian)

        if (entity != null) {
            return entity
        }

        val director = DirectorEntity(
            name = localizeService.localize(localizedData)
        )

        return directorRepository.save(director)
    }
}