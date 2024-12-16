package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime
import ru.melowetty.filmswishlistservice.model.Rating

@Schema(description = "Фильм")
data class UserFilmDto(
    @Schema(description = "Название фильма", example = "Стражи Галактики")
    override val title: String,
    @Schema(description = "Описание", example = "Какое-то большое описание...")
    override val description: String?,
    @Schema(description = "Идентификатор в IMDB", example = "tt12025053")
    override val imdbId: String,
    @Schema(description = "Возрастное ограничение", example = "TVMA")
    override val rating: Rating?,
    @Schema(description = "Год выхода", example = "2022")
    override val year: Int,
    @Schema(description = "Дата премьеры", example = "2024-10-24")
    override val released: LocalDate,
    @Schema(description = "Жанры")
    override val genres: List<GenreDto>,
    @Schema(description = "Страны")
    override val countries: List<CountryDto>,
    @Schema(description = "Режиссёры")
    override val directors: List<DirectorDto>,
    @Schema(description = "Сценаристы")
    override val writers: List<WriterDto>,
    @Schema(description = "Актёры")
    override val actors: List<ActorDto>,
    @Schema(description = "Языки")
    override val languages: List<LanguageDto>,
    @Schema(description = "Продолжительность в минутах", example = "120")
    override val durationInMinutes: Int?,
    @Schema(description = "Постер", example = "https://....")
    override val posterLink: String?,
    @Schema(description = "Рейтинг в IMDB", example = "8.5")
    override val imdbRating: Float?,
    @Schema(description = "Отметка о наличии в виш листе", example = "true")
    override val isWished: Boolean,
    @Schema(description = "Информация о фильме для пользователя")
    override val wishDetails: WishDetailsDto?,
    @Schema(description = "Кассовые сборы, в $", example = "12435845")
    val boxOffice: Long?
) : UserMovieDto(
    imdbId,
    title,
    description,
    rating,
    year,
    released,
    genres,
    countries,
    directors,
    writers,
    actors,
    languages,
    durationInMinutes,
    posterLink,
    imdbRating,
    isWished,
    wishDetails,
)
