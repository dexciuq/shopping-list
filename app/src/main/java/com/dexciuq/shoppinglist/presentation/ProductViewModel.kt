package com.dexciuq.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dexciuq.shoppinglist.domain.model.Product
import com.dexciuq.shoppinglist.domain.usecase.AddProductUseCase
import com.dexciuq.shoppinglist.domain.usecase.GetProductUseCase
import com.dexciuq.shoppinglist.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val addProductUseCase: AddProductUseCase,
) : ViewModel() {

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

    fun getProduct(id: Int) = viewModelScope.launch {
        val product = getProductUseCase(id)
        _product.value = product
    }

    fun updateProduct(nameInput: String?, quantityInput: String?) = viewModelScope.launch {
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

    fun addProduct(nameInput: String?, quantityInput: String?) = viewModelScope.launch {
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