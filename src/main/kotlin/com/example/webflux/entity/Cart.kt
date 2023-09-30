package com.example.webflux.entity

import org.springframework.data.annotation.Id

data class Cart(
    @Id
    val id: String,

    val cartItems: List<CartItem> = mutableListOf()
)