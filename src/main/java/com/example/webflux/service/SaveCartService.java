package com.example.webflux.service;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.BlockingItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveCartService {
    private final BlockingItemRepository repository;

    public SaveCartService(BlockingItemRepository repository) {
        this.repository = repository;
    }

    public void saveItem() {
        repository.save(Item.builder()
                .name("Alf alarm clock")
                .price(19.99)
                .build());
        repository.save(Item.builder()
                .name("Smurf TV tray")
                .price(24.99)
                .build());
    }
}
