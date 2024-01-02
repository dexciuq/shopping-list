package com.dexciuq.shoppinglist.domain.usecase

import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import com.dexciuq.shoppinglist.domain.model.Product
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Product {
        return productRepository.getProduct(id)
    }
}