package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.service.TranslatorService

@Primary
@Service
class CacheableTranslatorService(
    private val translatorService: TranslatorService
) : TranslatorService {
    private val logger = KotlinLogging.logger {  }

    override fun translate(from: Language, to: Language, texts: List<String>): List<String> {
        logger.info { "Получение данных из кэша" }
        return translatorService.translate(from, to, texts)
    }
}