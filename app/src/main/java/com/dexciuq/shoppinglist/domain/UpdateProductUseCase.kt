package com.dexciuq.shoppinglist.domain

class UpdateProductUseCase(
    private val productRepository: ProductRepository
){
    operator fun invoke(product: Product) {
        return productRepository.updateProduct(product)
    }
}