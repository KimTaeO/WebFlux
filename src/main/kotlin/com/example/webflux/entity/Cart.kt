package com.example.webflux.entity

import org.springframework.data.annotation.Id

class Cart(
    @Id
    val id: String?,

    val cartItems: List<CartItem>
) {
    constructor(id: String?): this(
        id = id,
        cartItems = arrayListOf()
    )

    class CartItem(
        val item: Item?,

        val quantity: Int?
    ) {
        constructor(item: Item?): this(
            item = item,
            quantity = 1
        )
    }
}