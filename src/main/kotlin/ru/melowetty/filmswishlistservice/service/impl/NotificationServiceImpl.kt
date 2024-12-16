package ru.melowetty.filmswishlistservice.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.melowetty.filmswishlistservice.dto.UserInfo
import ru.melowetty.filmswishlistservice.model.KafkaNotification
import ru.melowetty.filmswishlistservice.notification.Notification
import ru.melowetty.filmswishlistservice.notification.TelegramNotificationService
import ru.melowetty.filmswishlistservice.notification.model.MovieIsReleasedNotification
import ru.melowetty.filmswishlistservice.notification.model.MoviesChangesNotification
import ru.melowetty.filmswishlistservice.service.NotificationService

@Service
class NotificationServiceImpl(
    private val telegramNotificationService: TelegramNotificationService,
    private val kafkaTemplate: KafkaTemplate<String, KafkaNotification>,
    private val objectMapper: ObjectMapper
): NotificationService {
    private val logger = KotlinLogging.logger {  }

    override fun notify(user: UserInfo, notification: Notification) {
        kafkaTemplate.send("notification", KafkaNotification(user, notification, notification::class.java))
    }

    @KafkaListener(topics = ["notification"], groupId = "films-wish-list-service")
    fun consumeNotificationFromKafka(notification: KafkaNotification) {
        val dataAsStr = objectMapper.writeValueAsString(notification.notification)
        val notificationData = objectMapper.readValue(dataAsStr, notification.cls)

        logger.info { "Consume notification ${notification.notification} for user ${notification.user}" }

        telegramNotificationService.notify(notification.user, notificationData)
    }
}