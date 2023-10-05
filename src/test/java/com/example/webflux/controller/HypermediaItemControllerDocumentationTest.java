package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest(controllers = HypermediaItemController.class)
@AutoConfigureRestDocs
public class HypermediaItemControllerDocumentationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    void findOneItem() {
        when(itemRepository.findById("item-1")).thenReturn(Mono.just(
                Item.builder()
                        .id("item-1")
                        .name("Alf alarm clock")
                        .description("nothing I really need")
                        .price(19.99)
                        .build()
        ));

        webTestClient.get().uri("/hypermedia/items/item-1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findOne-hypermedia", preprocessResponse(prettyPrint()),
                        links(
                                linkWithRel("self").description("이 `Item`에 대한 공식 링크"),
                                linkWithRel("item").description("`Item` 목록 링크")
                        )));
    }

    @Test
    void profile() {
        webTestClient.get().uri("/hypermedia/items/profile")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("profile-alps", preprocessResponse(prettyPrint())));
    }
}
