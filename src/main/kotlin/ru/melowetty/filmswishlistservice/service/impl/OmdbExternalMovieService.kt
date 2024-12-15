package ru.melowetty.filmswishlistservice.service.impl

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder
import ru.melowetty.filmswishlistservice.exception.ExternalApiErrorException
import ru.melowetty.filmswishlistservice.model.BufferedTranslateTask
import ru.melowetty.filmswishlistservice.model.ExternalFilm
import ru.melowetty.filmswishlistservice.model.ExternalMovie
import ru.melowetty.filmswishlistservice.model.ExternalSeries
import ru.melowetty.filmswishlistservice.model.ExternalShortMovie
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.model.MovieType
import ru.melowetty.filmswishlistservice.model.Rating
import ru.melowetty.filmswishlistservice.service.ExternalMovieService
import ru.melowetty.filmswishlistservice.service.TranslatorService
import ru.melowetty.filmswishlistservice.structure.BufferedTranslator

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

            val years = it.year.split("-")
            val year = years.first().toInt()
            val lastYear = years.getOrNull(1)?.toInt()

            ExternalShortMovie(
                title = LocalizedData(
                    english = originalTitle,
                    russian = translatedTitle,
                ),
                imdbId = it.imdbId,
                year = year,
                lastYear = lastYear,
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
        val uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParam("apiKey", apiKey)
            .queryParam("i", imdbId)
            .encode()
            .toUriString()

        val response = retryTemplate.execute<OmdbMovieDetailInfo, RestClientException> {
            restTemplate.getForObject(uri, OmdbMovieDetailInfo::class.java)
        } ?: throw ExternalApiErrorException("api.omdb.detail-info.parse-error")

        val type = getMovieTypeByRawValue(response.type)

        val duration = getDuration(response.runtimeInMinutes)

        val (year, lastYear) = getMovieTime(response.year)

        val rating = omdbRatingToInternalRating(response.rated)

        val bufferedTranslator = BufferedTranslator(translatorService)

        val russianTitle = bufferedTranslator.translateTask(Language.ENGLISH, Language.RUSSIAN, response.title)
        val russianDescription = bufferedTranslator.translateTask(Language.ENGLISH, Language.RUSSIAN, response.description)

        val (genres, russianGenres) = parseStrAndMakeTranslateTask(bufferedTranslator, response.genres)

        val (countries, russianCountries) = parseStrAndMakeTranslateTask(bufferedTranslator, response.countries)

        val (directors, russianDirectors) = parseStrAndMakeTranslateTask(bufferedTranslator, response.directors)

        val (writers, russianWriters) = parseStrAndMakeTranslateTask(bufferedTranslator, response.writers)

        val (actors, russianActors) = parseStrAndMakeTranslateTask(bufferedTranslator, response.actors)

        val (languages, russianLanguages) = parseStrAndMakeTranslateTask(bufferedTranslator, response.languages)

        bufferedTranslator.translate()

        val title = LocalizedData(
            english = response.title,
            russian = russianTitle.get()
        )

        val description = LocalizedData(
            english = response.description,
            russian = russianDescription.get()
        )

        val translatedGenres = getTranslatedData(genres, russianGenres)
        val translatedCountries = getTranslatedData(countries, russianCountries)
        val translatedDirectors = getTranslatedData(directors, russianDirectors)
        val translatedWriters = getTranslatedData(writers, russianWriters)
        val translatedActors = getTranslatedData(actors, russianActors)
        val translatedLanguages = getTranslatedData(languages, russianLanguages)

        if (type == MovieType.FILM) {
            val boxOfficeAsStr = onlyNumRegex.find(response.boxOffice!!)?.value ?: "0"
            val boxOffice = boxOfficeAsStr.toInt()

            return ExternalFilm(
                imdbId = response.imdbID,
                title = title,
                description = description,
                rating = rating,
                year = year,
                released = response.released,
                genres = translatedGenres,
                countries = translatedCountries,
                directors = translatedDirectors,
                writers = translatedWriters,
                actors = translatedActors,
                languages = translatedLanguages,
                durationInMinutes = duration,
                posterLink = response.poster,
                imdbRating = response.imdbRating.toFloatOrNull(),
                type = type,
                boxOffice = boxOffice.toLong()
            )
        }

        else {
            return ExternalSeries(
                imdbId = response.imdbID,
                title = title,
                description = description,
                rating = rating,
                year = year,
                released = response.released,
                genres = translatedGenres,
                countries = translatedCountries,
                directors = translatedDirectors,
                writers = translatedWriters,
                actors = translatedActors,
                languages = translatedLanguages,
                durationInMinutes = duration,
                posterLink = response.poster,
                imdbRating = response.imdbRating.toFloatOrNull(),
                type = type,
                seasonsCount = response.totalSeasons?.toIntOrNull(),
                lastYear = lastYear ?: year
            )
        }
    }

    private fun getTranslatedData(english: List<String>, russian: List<BufferedTranslateTask>): List<LocalizedData> {
        return english.zip(russian).map {
            LocalizedData(
                english = it.first,
                russian = it.second.get()
            )
        }
    }

    private fun parseStrAndMakeTranslateTask(translator: BufferedTranslator, str: String):
            Pair<List<String>, List<BufferedTranslateTask>> {
        val list = parseStrToList(str)
        val russianList = list.map {
            translator.translateTask(Language.ENGLISH, Language.RUSSIAN, it)
        }

        return Pair(list, russianList)
    }

    private fun parseStrToList(str: String): List<String> {
        return str.split(",").map { it.trim() }.filter { it == "N/A" }
    }

    private fun getMovieTime(rawYearStr: String): MovieTime {
        val years = rawYearStr.split("-")
        val year = years.first().toInt()
        val lastYear = years.getOrNull(1)?.toInt()

        return MovieTime(year, lastYear)
    }

    private fun getDuration(rawDuration: String): Int? {
        val durationAsStr = onlyNumRegex.find(rawDuration)?.value ?: return null
        return durationAsStr.toIntOrNull()
    }

    private fun omdbRatingToInternalRating(ratingAsStr: String): Rating? {
        return try {
            Rating.valueOf(ratingAsStr.replace("-", ""))
        } catch (ex: IllegalArgumentException) {
            return null
        }
    }

    companion object {
        private val onlyNumRegex = "\\d+".toRegex()
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

    data class OmdbMovieDetailInfo(
        @JsonProperty("Title")
        val title: String,

        @JsonProperty("Year")
        val year: String,

        @JsonProperty("Rated")
        val rated: String,

        @JsonProperty("Released")
        @JsonFormat(pattern = "dd MMM yyyy", locale = "US")
        val released: LocalDate,

        @JsonProperty("Runtime")
        val runtimeInMinutes: String,

        @JsonProperty("Genre")
        val genres: String,
        @JsonProperty("Director")

        val directors: String,

        @JsonProperty("Writer")
        val writers: String,

        @JsonProperty("Actors")
        val actors: String,

        @JsonProperty("Plot")
        val description: String,

        @JsonProperty("Language")
        val languages: String,

        @JsonProperty("Country")
        val countries: String,

        @JsonProperty("Poster")
        val poster: String?,

        val imdbRating: String,

        val imdbID: String,

        @JsonProperty("Type")
        val type: String,

        @JsonProperty("BoxOffice")
        val boxOffice: String?,

        @JsonProperty("totalSeasons")
        val totalSeasons: String?
    )

    data class MovieTime(
        val year: Int,
        val lastYear: Int?
    )
}