package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ApiItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/api/items/{id}")
    Mono<Item> findOne(@PathVariable String id) {
        return itemRepository.findById(id);
    }

    @PostMapping("/api/items")
    Mono<ResponseEntity<?>> addNewItem(@RequestBody Mono<Item> item) {
        return item.flatMap(itemRepository::save)
                .map(savedItem -> ResponseEntity
                        .created(
                                URI.create("api/items/" +
                                savedItem.getId())
                        )
                        .body(savedItem));
    }

    @PutMapping("/api/items/{id}")
    public Mono<ResponseEntity<?>> updateItem(
            @RequestBody Mono<Item> item,
            @PathVariable String id
    ) {
        return item.map(content -> Item.builder()
                .id(id)
                .name(content.getName())
                .description(content.getDescription())
                .build())
                .flatMap(itemRepository::save)
                .map(ResponseEntity::ok);
    }
}
