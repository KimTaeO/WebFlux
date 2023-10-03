package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest(controllers = ApiItemController.class)
@AutoConfigureRestDocs
public class ApiItemControllerDocumentationTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ItemRepository repository;

    @Test
    void findingAllItems() {
        when(repository.findAll()).thenReturn(
                Flux.just(Item.builder()
                        .id("item-1")
                        .name("Alf alarm clock")
                        .description("nothing I really need")
                        .price(19.99)
                        .build()));

        webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findAll", preprocessResponse(prettyPrint())));
    }

    @Test
    void postNewItem() {
        when(repository.save(any())).thenReturn(
                Mono.just(Item.builder()
                        .id("1")
                        .name("Alf alarm clock")
                        .description("nothing important")
                        .price(19.99)
                        .build()));

        webTestClient.post().uri("/api/items")
                .bodyValue(Item.builder()
                        .name("Alf alarm clock")
                        .description("nothing important")
                        .price(19.99)
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(document("post-new-item", preprocessResponse(prettyPrint())));
    }
}
