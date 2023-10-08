package com.dexciuq.shoppinglist.domain

class AddProductUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(product: Product) {
        return productRepository.addProduct(product)
    }
}