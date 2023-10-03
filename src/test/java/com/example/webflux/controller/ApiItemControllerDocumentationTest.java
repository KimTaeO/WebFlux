package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static reactor.core.publisher.Mono.when;

@WebFluxTest(controllers = ApiItemController.class)
@AutoConfigureRestDocs
public class ApiItemControllerDocumentationTest {
    private WebTestClient webTestClient;

    @MockBean
    private ItemRepository repository;

    @Test
    void findingAllItems() {
        when(repository.findAll()).thenReturn(
                Flux.just(Item.builder()
                        .name("item-1")
                        .description("Alf alarm clock")
                        .price(19.99)
                        .build()));

        webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("finAll", preprocessResponse(prettyPrint())));
    }
}
