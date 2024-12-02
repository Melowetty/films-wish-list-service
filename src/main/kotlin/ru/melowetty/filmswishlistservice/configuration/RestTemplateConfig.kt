package ru.melowetty.filmswishlistservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.backoff.ExponentialBackOffPolicy
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun retryTemplate(): RetryTemplate {
        val template = RetryTemplate()

        val retryPolicy = SimpleRetryPolicy()
        template.setRetryPolicy(retryPolicy)

        val backOffPolicy = ExponentialBackOffPolicy()
        backOffPolicy.initialInterval = 500
        template.setBackOffPolicy(backOffPolicy)

        return template
    }
}