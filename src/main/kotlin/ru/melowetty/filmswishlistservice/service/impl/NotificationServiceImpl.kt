package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.notification.Notification
import ru.melowetty.filmswishlistservice.notification.TelegramNotificationService
import ru.melowetty.filmswishlistservice.service.NotificationService

@Service
class NotificationServiceImpl(
    private val telegramNotificationService: TelegramNotificationService
): NotificationService {
    private val logger = KotlinLogging.logger {  }

    override fun notify(user: UserInfo, notification: Notification) {
        
    }

    fun consumeNotificationFromKafka(notification: KafkaNotification) {
        logger.info { "Consume notification ${notification.notification} for user ${notification.user}" }

        telegramNotificationService.notify(notification.user, notification.notification)
    }

    data class KafkaNotification(
        val user: UserInfo,
        val notification: Notification
    )
}