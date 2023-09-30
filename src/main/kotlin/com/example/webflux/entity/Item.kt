package com.example.webflux.entity

import org.springframework.data.annotation.Id


data class Item(
    @Id
    val id: String = "",

    val name: String,

    val price: Double
)