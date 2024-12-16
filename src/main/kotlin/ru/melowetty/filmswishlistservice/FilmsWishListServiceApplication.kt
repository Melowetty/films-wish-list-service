package ru.melowetty.filmswishlistservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class FilmsWishListServiceApplication

fun main(args: Array<String>) {
    runApplication<FilmsWishListServiceApplication>(*args)
    //http://www.omdbapi.com/?plot=full&i=tt10048342
    //http://www.omdbapi.com/?plot=full&i=tt0468569
}
