package com.example.webflux.repository;

import com.example.webflux.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
