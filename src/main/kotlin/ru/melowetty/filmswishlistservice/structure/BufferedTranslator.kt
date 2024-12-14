package ru.melowetty.filmswishlistservice.structure

import ru.melowetty.filmswishlistservice.model.BufferedTranslateTask
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.service.TranslatorService

class BufferedTranslator(
    private val translatorService: TranslatorService
) {
    private val tasks: MutableList<Task> = mutableListOf()
    private var results: Map<Int, String> = hashMapOf()
    private var counter: Int = 0

    fun translateTask(from: Language, to: Language, text: String): BufferedTranslateTask {
        val id = counter
        val task = Task(
            id = id,
            from, to, text
        )

        tasks.add(task)

        counter += 1

        return BufferedTranslateTask(this, id)
    }

    fun translate() {
        results = tasks.groupBy { Key(it.from, it.to) }.map { (key, tasks) ->
            translatorService.translate(key.from, key.to, tasks.map { it.text }).zip(tasks)
        }.flatten().associate { Pair(it.second.id, it.first) }
    }

    fun getResult(task: BufferedTranslateTask): String {
        return results[task.id]!!
    }

    data class Key(
        val from: Language,
        val to: Language,
    )

    data class Task(
        val id: Int,
        val from: Language,
        val to: Language,
        val text: String,
    )

    data class Result(
        val id: Int,
        val text: String,
    )
}