package com.example.webflux.repository

import com.example.webflux.entity.Cart
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CartRepository : ReactiveCrudRepository<Cart, String> {
}