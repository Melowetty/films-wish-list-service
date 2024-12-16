package ru.melowetty.filmswishlistservice.notification

import mu.KotlinLogging
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Service
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.processor.TelegramNotificationProcessor

@Service
class TelegramNotificationService(
    private val env: Environment,
    private val processors: List<TelegramNotificationProcessor<out Notification>>
) {
    private val logger = KotlinLogging.logger {  }

    private val botToken: String = env["notification.telegram.bot-token"]!!.toString()

    private val telegramBot = OkHttpTelegramClient(botToken)

    private val processorMap: Map<Class<out Notification>, TelegramNotificationProcessor<out Notification>> = processors.associateBy { it.getNotificationClass() }

    fun <T : Notification> getProcessor(notification: T): TelegramNotificationProcessor<T>? {
        return processorMap[notification::class.java] as? TelegramNotificationProcessor<T>
    }

    fun notify(user: UserInfo, data: Notification) {
        if (user.telegramId == null) {
            logger.warn { "Пользователю прислано уведомление, но у него не выбран Telegram ID" }
            return
        }

        val processor = getProcessor(data)
            ?: throw IllegalArgumentException("No processor found for notification type: ${data::class.java}")

        try {
            processor.process(telegramBot, user, data)
        } catch (e: TelegramApiRequestException) {
            logger.warn(e) { "Catch exception, when send notification to telegram" }
        }
    }
}