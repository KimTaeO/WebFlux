package com.example.webflux.entity

import org.springframework.data.annotation.Id


data class Item(
    @Id
    private val id: String,

    private val name: String,

    private val price: Double
)