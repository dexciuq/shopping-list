package com.dexciuq.shoppinglist.domain

class DeleteProductUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int) {
        return productRepository.deleteProduct(id)
    }
}