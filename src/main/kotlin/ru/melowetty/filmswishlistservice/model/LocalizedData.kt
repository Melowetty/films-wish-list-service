package ru.melowetty.filmswishlistservice.model

import java.util.Locale

data class LocalizedData(
    val english: String,
    val russian: String
) {
    fun getDataByLocale(locale: Locale): String {
        val russianLocale = Locale("ru", "RU")
        return if (locale == russianLocale) {
            russian
        } else {
            english
        }
    }
}
