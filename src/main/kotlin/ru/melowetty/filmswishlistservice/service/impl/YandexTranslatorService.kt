package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.service.TranslatorService

@Service
class YandexTranslatorService : TranslatorService {
    private val logger = KotlinLogging.logger {  }

    override fun translate(from: Language, to: Language, texts: List<String>): List<String> {
        logger.info { "Получение данных из Yandex Translator" }
        return texts.map { "Translated $it" }
    }
}