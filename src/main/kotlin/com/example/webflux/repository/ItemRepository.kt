package com.example.webflux.repository

import com.example.webflux.entity.Item
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ItemRepository : ReactiveCrudRepository<Item, String> {
}