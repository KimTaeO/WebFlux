package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.alps.Alps;
import org.springframework.hateoas.mediatype.alps.Type;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mediatype.alps.Alps.alps;
import static org.springframework.hateoas.mediatype.alps.Alps.descriptor;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class HypermediaItemController {
    private final ItemRepository repository;


    @GetMapping("/hypermedia/items/{id}")
    Mono<EntityModel<Item>> findOne(@PathVariable String id) {
        HypermediaItemController controller = methodOn(HypermediaItemController.class);

        Mono<Link> selfLink = linkTo(controller.findOne(id)).withSelfRel().toMono();

        Mono<Link> aggregateLink = linkTo(controller.findAll())
                .withRel(IanaLinkRelations.ITEM).toMono();

        return Mono.zip(repository.findById(id), selfLink, aggregateLink)
                .map(o -> EntityModel.of(o.getT1(), Links.of(o.getT2(), o.getT3())));
    }

    @GetMapping("/hypermedia/api/items")
    Flux<Item> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/hypermedia/items/profile", produces = MediaTypes.ALPS_JSON_VALUE)
    public Alps profile() {
        return alps()
                .descriptor(Collections.singletonList(descriptor()
                        .id(Item.class.getSimpleName() + "-repr")
                        .descriptor(Arrays.stream(
                                Item.class.getDeclaredFields())
                                .map(   field -> descriptor()
                                        .name(field.getName())
                                        .type(Type.SEMANTIC)
                                        .build())
                                .collect(Collectors.toList()))
                        .build()
                )).build();
    }
}
