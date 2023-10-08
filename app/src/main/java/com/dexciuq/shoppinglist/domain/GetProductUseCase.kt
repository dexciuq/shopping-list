package com.dexciuq.shoppinglist.domain

class GetProductUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int): Product {
        return productRepository.getProduct(id)
    }
}