package ru.melowetty.filmswishlistservice.service

import java.time.LocalDate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.wiremock.integrations.testcontainers.WireMockContainer
import ru.melowetty.filmswishlistservice.configuration.MultiThreadingConfig
import ru.melowetty.filmswishlistservice.configuration.RestTemplateConfig
import ru.melowetty.filmswishlistservice.model.ExternalFilm
import ru.melowetty.filmswishlistservice.model.ExternalSeries
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.model.MovieType
import ru.melowetty.filmswishlistservice.model.Rating
import ru.melowetty.filmswishlistservice.service.impl.OmdbExternalMovieService

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = [OmdbExternalMovieService::class,
    RestTemplateConfig::class, MultiThreadingConfig::class])
@ActiveProfiles("test", "api", "dev")
@ExtendWith(MockitoExtension::class)
class OmdbExternalMovieServiceTest {
    @MockBean
    lateinit var translatorService: TranslatorService

    @Autowired
    lateinit var omdbExternalMovieService: OmdbExternalMovieService

    @Test
    fun `test search`() {
        Mockito.`when`(translatorService.translate(Language.ENGLISH, Language.RUSSIAN, listOf("The Queen's Gambit", "Gambit")))
            .thenReturn(listOf("Translated The Queen's Gambit", "Translated Gambit"))

        val results = omdbExternalMovieService.searchMovie("testQuery")

        Assertions.assertEquals(2, results.size)
        Assertions.assertEquals("The Queen's Gambit", results[0].title.english)
        Assertions.assertEquals("Translated The Queen's Gambit", results[0].title.russian)
        Assertions.assertEquals("tt10048342", results[0].imdbId)
        Assertions.assertEquals(MovieType.SERIES, results[0].type)
        Assertions.assertEquals(MovieType.FILM, results[1].type)
    }

    @Test
    fun `test get series by imdbId`() {
        Mockito.`when`(translatorService.translate(any(), any(), Mockito.anyList())).thenAnswer {
            return@thenAnswer (it.arguments.get(2) as ArrayList<Any>).map { "None" }
        }

        val result = omdbExternalMovieService.getMovieByImdbId("tt10048342")

        Assertions.assertEquals(result.imdbId, "tt10048342")
        Assertions.assertEquals(result.imdbRating, 8.5f)
        Assertions.assertEquals(result.type, MovieType.SERIES)
        Assertions.assertTrue(result is ExternalSeries)
        result as ExternalSeries

        Assertions.assertEquals(result.title.english, "The Queen's Gambit")
        Assertions.assertEquals(result.description?.english, "Test description")
        Assertions.assertEquals(result.genres.map { it.english }, listOf("Drama"))
        Assertions.assertTrue(result.directors.isEmpty())
        Assertions.assertEquals(result.writers.map { it.english }, listOf("Scott Frank", "Allan Scott"))
        Assertions.assertEquals(result.actors.map { it.english }, listOf("Anya Taylor-Joy", "Chloe Pirrie"))
        Assertions.assertEquals(result.languages.map { it.english }, listOf("English", "French"))
        Assertions.assertEquals(result.countries.map { it.english }, listOf("United States"))
        Assertions.assertEquals(result.durationInMinutes, 395)
        Assertions.assertEquals(result.year, 2020)
        Assertions.assertEquals(result.lastYear, 2020)
        Assertions.assertEquals(result.rating, Rating.TVMA)
        Assertions.assertEquals(result.released, LocalDate.of(2020, 10, 23))
    }

    @Test
    fun `test get film by imdbId`() {
        Mockito.`when`(translatorService.translate(any(), any(), Mockito.anyList())).thenAnswer {
            return@thenAnswer (it.arguments.get(2) as ArrayList<Any>).map { "None" }
        }

        val result = omdbExternalMovieService.getMovieByImdbId("tt2015381")

        Assertions.assertEquals(result.imdbId, "tt2015381")
        Assertions.assertEquals(result.imdbRating, 8f)
        Assertions.assertEquals(result.type, MovieType.FILM)
        Assertions.assertTrue(result is ExternalFilm)
        result as ExternalFilm

        Assertions.assertEquals(result.title.english, "Guardians of the Galaxy")
        Assertions.assertEquals(result.description?.english, "Test description")
        Assertions.assertEquals(result.genres.map { it.english }, listOf("Action", "Adventure", "Comedy"))
        Assertions.assertEquals(result.directors.map { it.english }, listOf("James Gunn"))
        Assertions.assertEquals(result.writers.map { it.english }, listOf("James Gunn", "Nicole Perlman"))
        Assertions.assertEquals(result.actors.map { it.english }, listOf("Chris Pratt", "Vin Diesel"))
        Assertions.assertEquals(result.languages.map { it.english }, listOf("English"))
        Assertions.assertEquals(result.countries.map { it.english }, listOf("United States"))
        Assertions.assertEquals(result.durationInMinutes, 121)
        Assertions.assertEquals(result.year, 2014)
        Assertions.assertEquals(result.rating, Rating.PG13)
        Assertions.assertEquals(result.released, LocalDate.of(2014, 8, 1))
    }

    companion object {
        @Container
        @JvmStatic
        var wireMock: WireMockContainer = WireMockContainer("wiremock/wiremock:3.2.0-alpine")
            .withMappingFromResource(OmdbExternalMovieServiceTest::class.java, "search.json")
            .withMappingFromResource(OmdbExternalMovieServiceTest::class.java, "detail-info-series.json")
            .withMappingFromResource(OmdbExternalMovieServiceTest::class.java, "detail-info-film.json")

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("api.omdb.base-url") { wireMock.getUrl("/search") }
            registry.add("api.omdb.api-key") { "test" }
        }

    }
}