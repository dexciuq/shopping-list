package com.dexciuq.shoppinglist.domain

interface ProductRepository {
    fun getProductList(): List<Product>
    fun getProduct(id: Int): Product
    fun addProduct(product: Product)
    fun updateProduct(product: Product)
    fun deleteProduct(id: Int)
}