package ru.melowetty.filmswishlistservice.service

import java.time.LocalDate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.wiremock.integrations.testcontainers.WireMockContainer
import ru.melowetty.filmswishlistservice.configuration.MultiThreadingConfig
import ru.melowetty.filmswishlistservice.entity.FilmEntity
import ru.melowetty.filmswishlistservice.entity.SeriesEntity
import ru.melowetty.filmswishlistservice.model.ExternalFilm
import ru.melowetty.filmswishlistservice.model.ExternalSeries
import ru.melowetty.filmswishlistservice.model.ExternalShortMovie
import ru.melowetty.filmswishlistservice.model.LocalizedData
import ru.melowetty.filmswishlistservice.model.MovieType
import ru.melowetty.filmswishlistservice.model.Rating
import ru.melowetty.filmswishlistservice.repository.MovieRepository
import ru.melowetty.filmswishlistservice.service.impl.MovieServiceImpl

@Testcontainers
@TestPropertySource(
    properties = [
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
    ]
)
@ActiveProfiles("test-with-db", "api", "dev", "oauth")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension::class)
class MovieServiceImplTest {
    @Autowired
    lateinit var movieRepository: MovieRepository

    @MockBean
    lateinit var externalMovieService: ExternalMovieService

    @Autowired
    lateinit var movieService: MovieServiceImpl

    @Test
    fun `search movie (film) and create`() {
        Mockito.`when`(externalMovieService.searchMovie("test")).thenReturn(
            listOf(ExternalShortMovie(
                title = LocalizedData(english = "test", russian = "test"),
                imdbId = "test2",
                year = 2022,
                lastYear = 2022,
                rating = Rating.PG,
                type = MovieType.FILM,
                genres = listOf(),
                actors = listOf(),
                countries = listOf(),
                directors = listOf(),
                durationInMinutes = 10,
                posterLink = null,
                imdbRating = 5f
            ))
        )

        Mockito.`when`(externalMovieService.getMovieByImdbId("test2")).thenReturn(
            ExternalFilm(
                title = LocalizedData(english = "test", russian = "test"),
                imdbId = "test2",
                year = 2022,
                rating = Rating.PG,
                type = MovieType.FILM,
                genres = listOf(LocalizedData(english = "testG", russian = "testG"),),
                actors = listOf(LocalizedData(english = "testA", russian = "testA"),),
                countries = listOf(LocalizedData(english = "testC", russian = "testC"),),
                directors = listOf(LocalizedData(english = "testD", russian = "testD"),),
                durationInMinutes = 10,
                posterLink = null,
                imdbRating = 5f,
                description = LocalizedData(english = "test", russian = "test"),
                released = LocalDate.of(2024, 10,10),
                writers = listOf(LocalizedData(english = "testW", russian = "testW"),),
                languages = listOf(LocalizedData(english = "testL", russian = "testL"),),
                boxOffice = 57,
            )
        )

        val entities = movieService.searchMovie("test")

        Assertions.assertTrue(entities.size == 1)

        val entity = entities.first()

        Assertions.assertEquals("test2", entity.imdbId)
        Assertions.assertEquals("test", entity.title.english)
        Assertions.assertEquals(2022, entity.year)
        Assertions.assertEquals(Rating.PG, entity.rating)
        Assertions.assertEquals("testG", entity.genres.first().name.english)
        Assertions.assertEquals("testA", entity.actors.first().name.english)
        Assertions.assertEquals("testC", entity.countries.first().name.english)
        Assertions.assertEquals("testD", entity.directors.first().name.english)
        Assertions.assertEquals("testW", entity.writers.first().name.english)
        Assertions.assertEquals("testL", entity.languages.first().name.english)
        Assertions.assertEquals(10, entity.durationInMinutes)
        Assertions.assertNull(entity.posterLink)
        Assertions.assertEquals(LocalDate.of(2024, 10, 10), entity.released)
        Assertions.assertEquals(5f, entity.imdbRating)
        Assertions.assertTrue(entity is FilmEntity)
        entity as FilmEntity
        Assertions.assertEquals(57, entity.boxOffice)

        val isCreated = movieRepository.existsByImdbId("test2")

        Assertions.assertTrue(isCreated)
    }

    @Test
    fun `search movie (series) and create`() {
        Mockito.`when`(externalMovieService.searchMovie("test2")).thenReturn(
            listOf(ExternalShortMovie(
                title = LocalizedData(english = "test", russian = "test"),
                imdbId = "test3",
                year = 2022,
                lastYear = 2022,
                rating = Rating.PG,
                type = MovieType.SERIES,
                genres = listOf(),
                actors = listOf(),
                countries = listOf(),
                directors = listOf(),
                durationInMinutes = 10,
                posterLink = null,
                imdbRating = 5f
            ))
        )

        Mockito.`when`(externalMovieService.getMovieByImdbId("test3")).thenReturn(
            ExternalSeries(
                title = LocalizedData(english = "test", russian = "test"),
                imdbId = "test2",
                year = 2022,
                rating = Rating.PG,
                type = MovieType.SERIES,
                genres = listOf(LocalizedData(english = "testG", russian = "testG"),),
                actors = listOf(LocalizedData(english = "testA", russian = "testA"),),
                countries = listOf(LocalizedData(english = "testC", russian = "testC"),),
                directors = listOf(LocalizedData(english = "testD", russian = "testD"),),
                durationInMinutes = 10,
                posterLink = null,
                imdbRating = 5f,
                description = LocalizedData(english = "test", russian = "test"),
                released = LocalDate.of(2024, 10,10),
                writers = listOf(LocalizedData(english = "testW", russian = "testW"),),
                languages = listOf(LocalizedData(english = "testL", russian = "testL"),),
                seasonsCount = 3,
                lastYear = 2025
            )
        )

        val entities = movieService.searchMovie("test2")

        Assertions.assertTrue(entities.size == 1)

        val entity = entities.first()

        Assertions.assertEquals("test3", entity.imdbId)
        Assertions.assertEquals("test", entity.title.english)
        Assertions.assertEquals(2022, entity.year)
        Assertions.assertEquals(Rating.PG, entity.rating)
        Assertions.assertEquals("testG", entity.genres.first().name.english)
        Assertions.assertEquals("testA", entity.actors.first().name.english)
        Assertions.assertEquals("testC", entity.countries.first().name.english)
        Assertions.assertEquals("testD", entity.directors.first().name.english)
        Assertions.assertEquals("testW", entity.writers.first().name.english)
        Assertions.assertEquals("testL", entity.languages.first().name.english)
        Assertions.assertEquals(10, entity.durationInMinutes)
        Assertions.assertNull(entity.posterLink)
        Assertions.assertEquals(LocalDate.of(2024, 10, 10), entity.released)
        Assertions.assertEquals(5f, entity.imdbRating)
        Assertions.assertTrue(entity is SeriesEntity)
        entity as SeriesEntity
        Assertions.assertEquals(3, entity.seasonsCount)
        Assertions.assertEquals(2025, entity.lastYear)

        val isCreated = movieRepository.existsByImdbId("test3")

        Assertions.assertTrue(isCreated)
    }
}