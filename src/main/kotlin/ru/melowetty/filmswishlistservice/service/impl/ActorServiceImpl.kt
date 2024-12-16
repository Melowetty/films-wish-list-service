package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.ActorEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.ActorRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService
import ru.melowetty.filmswishlistservice.service.LocalizeService

@Service
class ActorServiceImpl(
    private val actorRepository: ActorRepository,
    private val localizeService: LocalizeService
) : BaseLocalizedService<ActorEntity> {
    override fun getOrCreate(localizedData: LocalizedData): ActorEntity {
        val entity = actorRepository
            .findByName_EnglishAndName_Russian(localizedData.english, localizedData.russian)

        if (entity != null) {
            return entity
        }

        val actor = ActorEntity(
            name = localizeService.localize(localizedData)
        )

        return actorRepository.save(actor)
    }
}