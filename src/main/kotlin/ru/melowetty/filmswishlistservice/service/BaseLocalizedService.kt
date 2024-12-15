package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.entity.base.BaseLocalizedNamedEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData

interface BaseLocalizedService<T : BaseLocalizedNamedEntity<Long>> {
    fun getOrCreate(localizedData: LocalizedData): T
}