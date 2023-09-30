package com.example.webflux.controller;

import com.example.webflux.entity.Cart;
import com.example.webflux.repository.CartRepository;
import com.example.webflux.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home")
                .modelAttribute("items",
                        itemRepository.findAll())
                .modelAttribute("cart",
                        cartRepository.findById("My Cart")
                                .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
}
