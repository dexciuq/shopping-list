package com.dexciuq.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetProductListUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): LiveData<List<Product>> {
        return productRepository.getProductList()
    }
}