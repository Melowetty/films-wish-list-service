package ru.melowetty.filmswishlistservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FilmsWishListServiceApplication

fun main(args: Array<String>) {
    runApplication<FilmsWishListServiceApplication>(*args)
}
