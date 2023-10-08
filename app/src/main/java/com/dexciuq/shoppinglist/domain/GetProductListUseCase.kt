package com.dexciuq.shoppinglist.domain

class GetProductListUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): List<Product> {
        return productRepository.getProductList()
    }
}