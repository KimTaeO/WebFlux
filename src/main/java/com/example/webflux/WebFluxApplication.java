package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import reactor.blockhound.BlockHound;
import reactor.core.scheduler.ReactorBlockHoundIntegration;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.webflux.repository", "com.example.webflux.controller", "com.example.webflux.service"})
public class WebFluxApplication {

    public static void main(String[] args) {
        BlockHound.install();

        SpringApplication.run(WebFluxApplication.class, args);
    }

}
