package ru.melowetty.filmswishlistservice.service

import ru.melowetty.filmswishlistservice.model.Language

interface TranslatorService {
    fun translate(from: Language, to: Language, texts: List<String>): List<String>
}