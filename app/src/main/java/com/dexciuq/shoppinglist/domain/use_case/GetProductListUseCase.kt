package com.dexciuq.shoppinglist.domain.use_case

import androidx.lifecycle.LiveData
import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import com.dexciuq.shoppinglist.domain.model.Product

class GetProductListUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): LiveData<List<Product>> {
        return productRepository.getProductList()
    }
}