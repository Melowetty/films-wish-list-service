package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.entity.WriterEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.repository.WriterRepository
import ru.melowetty.filmswishlistservice.service.BaseLocalizedService

@Service
class WriterServiceImpl(
    private val writerRepository: WriterRepository,
    private val localizeService: LocalizeServiceImpl
) : BaseLocalizedService<WriterEntity> {
    override fun getOrCreate(localizedData: LocalizedData): WriterEntity {
        val entity = writerRepository
            .findByName_EnglishAndName_Russian(localizedData.english, localizedData.russian)

        if (entity != null) {
            return entity
        }

        val writer = WriterEntity(
            name = localizeService.localize(localizedData)
        )

        return writerRepository.save(writer)
    }
}