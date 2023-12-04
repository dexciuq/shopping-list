package com.dexciuq.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dexciuq.shoppinglist.data.ProductRepositoryImpl
import com.dexciuq.shoppinglist.domain.AddProductUseCase
import com.dexciuq.shoppinglist.domain.GetProductUseCase
import com.dexciuq.shoppinglist.domain.Product
import com.dexciuq.shoppinglist.domain.UpdateProductUseCase

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepositoryImpl

    private val getProductUseCase = GetProductUseCase(productRepository)
    private val updateProductUseCase = UpdateProductUseCase(productRepository)
    private val addProductUseCase = AddProductUseCase(productRepository)

    private val _nameInputError = MutableLiveData<Boolean>()
    val nameInputError: LiveData<Boolean> = _nameInputError

    private val _quantityInputError = MutableLiveData<Boolean>()
    val quantityInputError: LiveData<Boolean> = _quantityInputError

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun resetNameInputError() {
        _nameInputError.value = false
    }

    fun resetQuantityInputError() {
        _quantityInputError.value = false
    }

    fun getProduct(id: Int) {
        val product = getProductUseCase(id)
        _product.value = product
    }

    fun updateProduct(nameInput: String?, quantityInput: String?) {
        val name = parseName(nameInput)
        val quantity = parseQuantity(quantityInput)

        if (validateInput(name, quantity)) {
            _product.value?.let {
                updateProductUseCase(
                    it.copy(name = name, quantity = quantity)
                )

                finishWork()
            }
        }
    }

    fun addProduct(nameInput: String?, quantityInput: String?) {
        val name = parseName(nameInput)
        val quantity = parseQuantity(quantityInput)

        if (validateInput(name, quantity)) {
            addProductUseCase(
                Product(
                    name = name,
                    quantity = quantity,
                    active = true
                )
            )

            finishWork()
        }
    }

    private fun parseName(name: String?): String = name?.trim() ?: ""

    private fun parseQuantity(quantity: String?): Double =
        try {
            quantity?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }

    private fun validateInput(name: String, quantity: Double): Boolean {
        var result = true
        if (name.isBlank()) {
            _nameInputError.value = true
            result = false
        }

        if (quantity <= 0) {
            _quantityInputError.value = true
            result = false
        }

        return result
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}