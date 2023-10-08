package com.dexciuq.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexciuq.shoppinglist.data.ProductRepositoryImpl
import com.dexciuq.shoppinglist.domain.AddProductUseCase
import com.dexciuq.shoppinglist.domain.DeleteProductUseCase
import com.dexciuq.shoppinglist.domain.GetProductListUseCase
import com.dexciuq.shoppinglist.domain.Product
import com.dexciuq.shoppinglist.domain.UpdateProductUseCase

class MainViewModel : ViewModel() {

    private val productRepository = ProductRepositoryImpl

    private val getProductListUseCase = GetProductListUseCase(productRepository)
    private val deleteProductUseCase = DeleteProductUseCase(productRepository)
    private val updateProductUseCase = UpdateProductUseCase(productRepository)

    val productList = getProductListUseCase()

    fun deleteProduct(id: Int) {
        deleteProductUseCase(id)
    }

    fun changeEnabledState(product: Product) {
        val newProduct = product.copy(active = !product.active)
        updateProductUseCase(newProduct)
    }

}