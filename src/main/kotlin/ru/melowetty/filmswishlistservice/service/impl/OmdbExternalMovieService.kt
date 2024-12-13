package ru.melowetty.filmswishlistservice.service.impl

import com.fasterxml.jackson.annotation.JsonProperty
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import ru.melowetty.filmswishlistservice.exception.ExternalApiErrorException
import ru.melowetty.filmswishlistservice.model.ExternalMovie
import ru.melowetty.filmswishlistservice.model.ExternalShortMovie
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.model.MovieType
import ru.melowetty.filmswishlistservice.service.ExternalMovieService
import ru.melowetty.filmswishlistservice.service.TranslatorService

@Service
class OmdbExternalMovieService(
    private val restTemplate: RestTemplate,
    private val retryTemplate: RetryTemplate,
    private val translatorService: TranslatorService
) : ExternalMovieService {
    private val logger = KotlinLogging.logger { }

    @Value("\${api.omdb.base-url}")
    private lateinit var baseUrl: String

    @Value("\${api.omdb.api-key}")
    private lateinit var apiKey: String

    override fun searchMovie(query: String): List<ExternalShortMovie> {
        logger.info { "Получение данных из OMDB" }
        val uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParam("apiKey", apiKey)
            .queryParam("s", query)
            .encode()
            .toUriString()

        val response = retryTemplate.execute<OmdbSearchResponse, RestClientException> {
            restTemplate.getForObject(uri, OmdbSearchResponse::class.java)
        } ?: throw ExternalApiErrorException("api.omdb.search.parse-error")

        if (response.response.not()) return listOf()
        if (response.search == null) {
            throw ExternalApiErrorException("api.omdb.search.parse-error")
        }

        val translatedTitles =
            translatorService.translate(Language.ENGLISH, Language.RUSSIAN, response.search.map { it.title })

        return response.search.mapIndexed { index, it ->
            val originalTitle = it.title
            val translatedTitle = translatedTitles[index]

            val year = it.year.split("–").first().toInt()

            ExternalShortMovie(
                title = LocalizedData(
                    english = originalTitle,
                    russian = translatedTitle,
                ),
                imdbId = it.imdbId,
                year = year,
                rating = null,
                genres = null,
                countries = null,
                directors = null,
                actors = null,
                durationInMinutes = null,
                posterLink = it.poster,
                imdbRating = null,
                type = getMovieTypeByRawValue(it.type.lowercase())
            )
        }
    }

    private fun getMovieTypeByRawValue(type: String): MovieType {
        return if (type == "movie") {
            MovieType.FILM
        } else {
            MovieType.SERIES
        }
    }

    override fun getMovieByImdbId(imdbId: String): ExternalMovie {
        logger.info { "Получение данных из OMDB" }
        TODO("Not yet implemented")
    }

    data class OmdbSearchResponse(
        @JsonProperty("Response")
        val response: Boolean,
        @JsonProperty("Search")
        val search: List<OmdbSearchResult>?
    )

    data class OmdbSearchResult(
        @JsonProperty("Title")
        val title: String,
        @JsonProperty("Year")
        val year: String,
        @JsonProperty("imdbID")
        val imdbId: String,
        @JsonProperty("Type")
        val type: String,
        @JsonProperty("Poster")
        val poster: String
    )
}