package com.example.webflux.entity

import org.springframework.data.annotation.Id

data class Cart(
    @Id
    private val id: String,

    private val cartItems: List<CartItem> = mutableListOf()
)