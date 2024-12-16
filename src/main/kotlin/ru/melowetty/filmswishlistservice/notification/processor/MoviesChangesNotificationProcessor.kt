package ru.melowetty.filmswishlistservice.notification.processor

import java.time.format.DateTimeFormatter
import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.model.MoviesChangesNotification

@Component
class MoviesChangesNotificationProcessor: TelegramNotificationProcessor<MoviesChangesNotification> {
    override fun process(bot: OkHttpTelegramClient, user: UserInfo, notification: MoviesChangesNotification) {
        val seriesChanges = notification.seriesChanges.map {
            "Сериал ${it.title} продлён до ${it.lastYear} года"
        }

        val filmsChanges = notification.movieChanges.map {
            "У фильма ${it.movieTitle} сдвинулась дата выхода, теперь он выходит " +
                    "${it.newDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}"
        }

        val message = SendMessage.builder()
            .chatId(user.telegramId!!)
            .text("*🔔 Сводка *\n" +
                    "За эту неделю произошли следующие изменения:\n" +
                    "\n" + seriesChanges.joinToString("\n") +
                "\n" + filmsChanges.joinToString("\n")
            )
            .parseMode(ParseMode.MARKDOWN)
            .build()

        bot.executeAsync(message)
    }

    override fun getNotificationClass(): Class<MoviesChangesNotification> {
        return MoviesChangesNotification::class.java
    }
}