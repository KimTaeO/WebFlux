package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(AffordancesItemController.class)
@AutoConfigureRestDocs
public class AffordancesItemControllerDocumentationTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void findSingleItemAffordances() {
        Item item = Item.builder()
                .id("item-1")
                .name("Alf alarm clock")
                .description("nothing I relly need")
                .price(19.99)
                .build();

        when(itemRepository.findById("item-1")).thenReturn(Mono.just(item));

        when(itemRepository.save(item)).thenReturn(Mono.just(item));

        webTestClient.get().uri("/affordances/items/item-1")
                .accept(MediaTypes.HAL_FORMS_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("single-item-affordances",
                        preprocessResponse(prettyPrint())));
    }

    @Test
    void addNewItemAffordances() {
        Item item = Item.builder()
                .id("item-1")
                .name("Alf alarm clock")
                .description("nothing I relly need")
                .price(19.99)
                .build();

        when(itemRepository.findById("item-1")).thenReturn(Mono.just(item));

        webTestClient.post().uri("/affordances/items")
                .accept(MediaTypes.HAL_FORMS_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(document("add-new-item-affordances",
                        preprocessResponse(prettyPrint())));
    }
}
