package ru.melowetty.filmswishlistservice.service.impl

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.model.KafkaNotification
import ru.melowetty.filmswishlistservice.notification.Notification
import ru.melowetty.filmswishlistservice.notification.TelegramNotificationService
import ru.melowetty.filmswishlistservice.service.NotificationService

@Service
class NotificationServiceImpl(
    private val telegramNotificationService: TelegramNotificationService,
    private val kafkaTemplate: KafkaTemplate<String, KafkaNotification>,
): NotificationService {
    private val logger = KotlinLogging.logger {  }

    override fun notify(user: UserInfo, notification: Notification) {
        kafkaTemplate.send("notification", KafkaNotification(user, notification))
    }

    @KafkaListener(topics = ["notification"], groupId = "films-wish-list-service")
    fun consumeNotificationFromKafka(notification: KafkaNotification) {
        logger.info { "Consume notification ${notification.notification} for user ${notification.user}" }

        telegramNotificationService.notify(notification.user, notification.notification)
    }
}