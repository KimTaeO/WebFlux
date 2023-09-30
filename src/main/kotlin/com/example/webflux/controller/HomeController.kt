package com.example.webflux.controller

import com.example.webflux.entity.Cart
import com.example.webflux.repository.CartRepository
import com.example.webflux.repository.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.Mono

@Controller
class HomeController(
    private val itemRepository: ItemRepository,
    private val cartRepository: CartRepository
) {
    @GetMapping
    fun home(): Mono<Rendering> = Mono.just(Rendering.view("home")
        .modelAttribute("items",
            itemRepository.findAll())
        .modelAttribute("cart",
            cartRepository.findById("My Cart")
                .defaultIfEmpty(Cart("My Cart")))
        .build())
}