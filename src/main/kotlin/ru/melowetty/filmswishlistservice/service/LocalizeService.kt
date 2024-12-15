package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.entity.LocalizedEntity
import ru.melowetty.filmswishlistservice.model.LocalizedData

interface LocalizeService {
    fun localize(data: LocalizedData): LocalizedEntity
}