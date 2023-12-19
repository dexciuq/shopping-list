package com.dexciuq.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.dexciuq.shoppinglist.domain.model.Product

interface ProductRepository {
    fun getProductList(): LiveData<List<Product>>
    suspend fun getProduct(id: Int): Product
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(id: Int)
}