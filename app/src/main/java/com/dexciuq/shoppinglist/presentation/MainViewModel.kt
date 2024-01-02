package com.dexciuq.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dexciuq.shoppinglist.domain.model.Product
import com.dexciuq.shoppinglist.domain.usecase.DeleteProductUseCase
import com.dexciuq.shoppinglist.domain.usecase.GetProductListUseCase
import com.dexciuq.shoppinglist.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
) : ViewModel() {

    val productList = getProductListUseCase()

    fun deleteProduct(id: Int) = viewModelScope.launch {
        deleteProductUseCase(id)
    }

    fun changeEnabledState(product: Product) = viewModelScope.launch {
        val newProduct = product.copy(active = !product.active)
        updateProductUseCase(newProduct)
    }
}