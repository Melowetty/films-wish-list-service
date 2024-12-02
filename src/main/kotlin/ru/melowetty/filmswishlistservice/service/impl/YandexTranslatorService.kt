package ru.melowetty.filmswishlistservice.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.melowetty.filmswishlistservice.exception.ExternalApiErrorException
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.service.TranslatorService

@Service
class YandexTranslatorService(
    private val restTemplate: RestTemplate,
    private val retryTemplate: RetryTemplate
) : TranslatorService {
    @Value("\${api.yandex-translator.base-url}")
    private lateinit var baseUrl: String

    @Value("\${api.yandex-translator.api-key}")
    private lateinit var apiKey: String

    override fun translate(from: Language, to: Language, texts: List<String>): List<String> {
        val body: Map<String, Any> =
            mapOf("targetLanguageCode" to to.code,
                "sourceLanguageCode" to from.code,
                "texts" to texts)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = "Api-Key $apiKey"
        val httpEntity = HttpEntity(body, headers)

        val res = retryTemplate.execute<YandexApiTranslationsResponse, RestClientException> {
            restTemplate.postForObject("$baseUrl/translate", httpEntity,
                YandexApiTranslationsResponse::class.java)
        } ?:
        throw ExternalApiErrorException("api.yandex-translate.translate.error")

        return res.translations.map { it.text }
    }

    data class YandexApiTranslationsResponse(
        val translations: List<YandexApiTranslateResponse>
    )

    data class YandexApiTranslateResponse(
        val text: String
    )
}