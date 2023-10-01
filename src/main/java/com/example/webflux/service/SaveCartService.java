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
        repository.save(new Item("Alf alarm clock", 19.99));
        repository.save(new Item("Smurf TV tray", 24.99));
    }
}
