package com.dexciuq.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dexciuq.shoppinglist.domain.Product
import com.dexciuq.shoppinglist.domain.ProductRepository
import kotlin.random.Random

object ProductRepositoryImpl : ProductRepository {

    private val productListLiveData = MutableLiveData<List<Product>>()
    private val productList = mutableListOf<Product>()
    private var autoIncrement = 1000

    init {
        repeat(1000) {
            val item = Product(name = "Name $it", quantity = it.toDouble(), active = Random.nextBoolean())
            addProduct(item)
        }
    }

    private fun updateList() {
        productListLiveData.value = productList.toList()
    }
    override fun getProductList(): LiveData<List<Product>> = productListLiveData

    override fun getProduct(id: Int): Product =
        productList.find {
            it.id == id
        } ?: error("Product with id: $id not found")

    override fun addProduct(product: Product) {
        if (product.id == Product.UNDEFINED_ID) {
            product.id = autoIncrement++
        }
        productList.add(product)
        updateList()
    }

    override fun updateProduct(product: Product) {
        val oldProduct = getProduct(product.id)
        productList.remove(oldProduct)
        addProduct(product)
    }

    override fun deleteProduct(id: Int) {
        val isRemoved = productList.removeIf {
            it.id == id
        }
        if (!isRemoved) error("Product with id: $id not found")
        updateList()
    }
}