package ru.melowetty.filmswishlistservice.service.impl

import kotlin.collections.set
import mu.KotlinLogging
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.service.TranslatorService

@Primary
@Service
class CacheableTranslatorService(
    private val translatorService: TranslatorService,
    private val cacheManager: CacheManager
) : TranslatorService {
    private val logger = KotlinLogging.logger { }

    override fun translate(from: Language, to: Language, texts: List<String>): List<String> {
        val cache = cacheManager.getCache("translations") ?: return directTranslate(from, to, texts)

        val translates = HashMap<String, String?>()

        for (text in texts) {
            translates[text] = cache.get(text)?.get() as String?
        }

        val nonCachedValues = translates.filter {
            it.value == null
        }.map { it.key }

        if (nonCachedValues.isNotEmpty()) {
            logger.info { "Получение переводов из внешнего источника" }
            val newTranslations = directTranslate(from, to, nonCachedValues)
            newTranslations.forEachIndexed { index, element ->
                translates[nonCachedValues[index]] = element
            }
        }

        return texts.map {
            translates[it] ?: it
        }
    }

    private fun directTranslate(from: Language, to: Language, texts: List<String>): List<String> {
        val result = translatorService.translate(from, to, texts)
        val cache = cacheManager.getCache("translations") ?: return result

        result.forEachIndexed { index, element ->
            cache.put(texts[index], element)
        }

        return result
    }
}