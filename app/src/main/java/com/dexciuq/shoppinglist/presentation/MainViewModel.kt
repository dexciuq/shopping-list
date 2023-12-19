package com.dexciuq.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dexciuq.shoppinglist.data.repository.ProductRepositoryImpl
import com.dexciuq.shoppinglist.domain.model.Product
import com.dexciuq.shoppinglist.domain.use_case.DeleteProductUseCase
import com.dexciuq.shoppinglist.domain.use_case.GetProductListUseCase
import com.dexciuq.shoppinglist.domain.use_case.UpdateProductUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val productRepository = ProductRepositoryImpl(application)

    private val getProductListUseCase = GetProductListUseCase(productRepository)
    private val deleteProductUseCase = DeleteProductUseCase(productRepository)
    private val updateProductUseCase = UpdateProductUseCase(productRepository)

    val productList = getProductListUseCase()

    fun deleteProduct(id: Int) = viewModelScope.launch {
        deleteProductUseCase(id)
    }

    fun changeEnabledState(product: Product) = viewModelScope.launch {
        val newProduct = product.copy(active = !product.active)
        updateProductUseCase(newProduct)
    }
}