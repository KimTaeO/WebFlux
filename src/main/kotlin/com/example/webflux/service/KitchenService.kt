package com.example.webflux.service

import com.example.webflux.entity.Dish
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.*


@Service
class KitchenService {
    fun getDishes(): Flux<Dish> {
        return Flux.generate<Dish> {
            it.next(randomDish())
        }.delayElements(Duration.ofMillis(250))
    }

    private fun randomDish(): Dish {
        return menu[picker.nextInt(menu.size)]
    }

    private val menu = arrayListOf(
        Dish("Sesame chicken"),
        Dish("Lo mein noodles, plain"),
        Dish("Sweet & sour beef")
    )

    private val picker = Random()
}