package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;
import reactor.core.scheduler.ReactorBlockHoundIntegration;

@SpringBootApplication
public class WebFluxApplication {

    public static void main(String[] args) {
        BlockHound.builder()
                .allowBlockingCallsInside(
                        TemplateEngine.class.getCanonicalName(), "process"
                ).install();

        SpringApplication.run(WebFluxApplication.class, args);
    }

}
