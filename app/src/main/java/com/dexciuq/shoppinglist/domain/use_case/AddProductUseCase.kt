package com.dexciuq.shoppinglist.domain.use_case

import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import com.dexciuq.shoppinglist.domain.model.Product

class AddProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        return productRepository.addProduct(product)
    }
}