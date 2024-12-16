package ru.melowetty.filmswishlistservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.melowetty.filmswishlistservice.model.Rating

@Schema(description = "Сериал")
data class UserSeriesShortDto(
    @Schema(description = "Название сериала", example = "Ход королевы")
    override val title: String,
    @Schema(description = "Идентификатор в IMDB", example = "tt12025053")
    override val imdbId: String,
    @Schema(description = "Год выхода", example = "2022")
    override val year: Int,
    @Schema(description = "Возрастное ограничение", example = "TVMA")
    override val rating: Rating?,
    @Schema(description = "Жанры")
    override val genres: List<String>,
    @Schema(description = "Страны")
    override val countries: List<String>,
    @Schema(description = "Режиссёры")
    override val directors: List<String>,
    @Schema(description = "Актёры")
    override val actors: List<String>,
    @Schema(description = "Сценаристы")
    override val writers: List<String>,
    @Schema(description = "Языки")
    override val languages: List<String>,
    @Schema(description = "Продолжительность в минутах", example = "120")
    override val durationInMinutes: Int?,
    @Schema(description = "Постер", example = "https://....")
    override val posterLink: String?,
    @Schema(description = "Рейтинг в IMDB", example = "8.5")
    override val imdbRating: Float?,
    @Schema(description = "Год стриминга последнего сезона", example = "2024")
    val lastYear: Int,
    @Schema(description = "Количество сезонов", example = "3")
    val seasonCount: Int?,
    @Schema(description = "Отметка о наличии в виш листе")
    override val isWished: Boolean,
    @Schema(description = "Информация о фильме для пользователя")
    override val wishDetails: WishDetailsDto?,
): UserMovieShortDto(
    title,
    imdbId,
    year,
    rating,
    genres,
    countries,
    directors,
    actors,
    writers,
    languages,
    durationInMinutes,
    posterLink,
    imdbRating,
    isWished,
    wishDetails
)
