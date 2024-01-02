package com.dexciuq.shoppinglist.domain.usecase

import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int) {
        return productRepository.deleteProduct(id)
    }
}