package com.dexciuq.shoppinglist.domain.usecase

import com.dexciuq.shoppinglist.domain.model.Product
import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        return productRepository.updateProduct(product)
    }
}