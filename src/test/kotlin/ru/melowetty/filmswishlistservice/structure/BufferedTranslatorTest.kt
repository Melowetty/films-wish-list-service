package ru.melowetty.filmswishlistservice.structure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.melowetty.filmswishlistservice.model.BufferedTranslateTask
import ru.melowetty.filmswishlistservice.model.Language
import ru.melowetty.filmswishlistservice.service.TranslatorService

@ExtendWith(MockitoExtension::class)
class BufferedTranslatorTest {
    @InjectMocks
    private lateinit var bufferedTranslator: BufferedTranslator

    @Mock
    private lateinit var translatorService: TranslatorService

    @Test
    fun `test creating task`() {
        bufferedTranslator.translateTask(Language.RUSSIAN, Language.ENGLISH, "test")

        Mockito.verify(translatorService, Mockito.never()).translate(Language.RUSSIAN, Language.ENGLISH, listOf("test"))
    }

    @Test
    fun `test make translate tasks`() {
        val task = bufferedTranslator.translateTask(Language.RUSSIAN, Language.ENGLISH, "test")

        Mockito.`when`(translatorService.translate(Language.RUSSIAN, Language.ENGLISH, listOf("test"))).thenReturn(
            listOf("Translated test")
        )

        bufferedTranslator.translate()

        Mockito.verify(translatorService, Mockito.times(1)).translate(Language.RUSSIAN, Language.ENGLISH, listOf("test"))

        val result = task.get()

        Assertions.assertEquals("Translated test", result)
    }
}