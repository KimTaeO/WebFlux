package com.example.webflux.entity

data class Dish(
    val description: String,
    val delivered: Boolean = false
) {
    companion object {
        fun deliver(dish: Dish): Dish {
            return Dish(
                description = dish.description,
                delivered = true
            )
        }

    }

    override fun toString(): String {
        return "Dish{" +
                "description=" + description + '\'' +
                ", delivered" + delivered +
                '}'
    }
}