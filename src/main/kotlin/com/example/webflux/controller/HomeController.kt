package com.example.webflux.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

@Controller
class HomeController {
    @GetMapping
    fun home(): Mono<String> {
        return Mono.just("home")
    }
}