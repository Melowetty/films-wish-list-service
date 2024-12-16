package ru.melowetty.filmswishlistservice.notification.processor

import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.model.MovieIsReleasedNotification

@Component
class MovieIsReleasedNotificationProcessor: TelegramNotificationProcessor<MovieIsReleasedNotification> {
    override fun process(bot: OkHttpTelegramClient, user: UserInfo, notification: MovieIsReleasedNotification) {
        val message = SendMessage.builder()
            .chatId(user.telegramId!!)
            .text("*🔔 Вышел фильм *\n" +
                    "Сегодня в прокат вышли следующие фильмы:\n" +
                    "\n" + notification.titles.joinToString("\n")
            + "\n\n Можешь смело идти на их премьеру!")
            .parseMode(ParseMode.MARKDOWN)
            .build()

        bot.executeAsync(message)
    }

    override fun getNotificationClass(): Class<MovieIsReleasedNotification> {
        return MovieIsReleasedNotification::class.java
    }

}