package com.dexciuq.shoppinglist.domain.use_case

import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import com.dexciuq.shoppinglist.domain.model.Product

class GetProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Product {
        return productRepository.getProduct(id)
    }
}