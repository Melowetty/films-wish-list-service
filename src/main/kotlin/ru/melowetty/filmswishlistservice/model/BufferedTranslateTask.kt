package ru.melowetty.filmswishlistservice.model

import ru.melowetty.filmswishlistservice.structure.BufferedTranslator

data class BufferedTranslateTask(
    private val instance: BufferedTranslator,
    val id: Int
) {
    fun get(): String {
        return instance.getResult(this)
    }
}
