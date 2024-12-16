package ru.melowetty.filmswishlistservice.notification.processor

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import ru.melowetty.filmswishlistservice.dto.UserInfo

interface TelegramNotificationProcessor<T> {
    fun process(bot: OkHttpTelegramClient, user: UserInfo, notification: T)
    fun getNotificationClass(): Class<T>
}