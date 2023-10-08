package com.dexciuq.shoppinglist.domain

data class Product(
    var id: Int = UNDEFINED_ID,
    val name: String,
    val quantity: Double,
    val active: Boolean,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
