package com.dexciuq.shoppinglist.domain.usecase

import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import com.dexciuq.shoppinglist.domain.model.Product
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        return productRepository.addProduct(product)
    }
}