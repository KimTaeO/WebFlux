package com.example.webflux.entity

data class CartItem(
    private val item: Item,
    private val quantity: Int
)