package com.dexciuq.shoppinglist.domain.use_case

import com.dexciuq.shoppinglist.domain.repository.ProductRepository

class DeleteProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int) {
        return productRepository.deleteProduct(id)
    }
}