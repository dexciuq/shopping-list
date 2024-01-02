package com.dexciuq.shoppinglist.domain.usecase

import androidx.lifecycle.LiveData
import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import com.dexciuq.shoppinglist.domain.model.Product
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): LiveData<List<Product>> {
        return productRepository.getProductList()
    }
}