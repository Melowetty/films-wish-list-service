package ru.melowetty.filmswishlistservice

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<FilmsWishListServiceApplication>().with(TestcontainersConfiguration::class).run(*args)
}
