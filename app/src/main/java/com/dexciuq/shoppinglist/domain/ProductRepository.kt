package com.dexciuq.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ProductRepository {
    fun getProductList(): LiveData<List<Product>>
    fun getProduct(id: Int): Product
    fun addProduct(product: Product)
    fun updateProduct(product: Product)
    fun deleteProduct(id: Int)
}