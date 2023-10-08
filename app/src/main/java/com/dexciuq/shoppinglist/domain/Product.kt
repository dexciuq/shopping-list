package com.dexciuq.shoppinglist.domain

data class Product(
    val id: Int,
    val name: String,
    val quantity: Double,
    val active: Boolean,
)
