package com.example.webflux.controller;

import com.example.webflux.entity.Dish;
import com.example.webflux.service.KitchenService;
import com.example.webflux.service.SaveCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {
    private final KitchenService kitchen;
    private final SaveCartService saveCartService;

    public ServerController(KitchenService kitchen, SaveCartService saveCartService) {
        this.kitchen = kitchen;
        this.saveCartService = saveCartService;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> serveDishes() {
        return this.kitchen.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliverDishes() {
        return this.kitchen.getDishes()
                .map(Dish::deliver);
    }

    @PostMapping("/save")
    ResponseEntity<Void> saveItem() {
        saveCartService.saveItem();
        return ResponseEntity.ok().build();
    }
}
