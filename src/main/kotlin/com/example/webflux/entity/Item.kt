package com.example.webflux.entity

import org.springframework.data.annotation.Id


class Item(
    @Id
    val id: String = "",

    val name: String?,

    val price: Double?
) {

    constructor(name: String?, price: Double): this(
        id = "",
        name = name,
        price = price

    )
}