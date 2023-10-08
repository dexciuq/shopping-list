package com.dexciuq.shoppinglist.data

import com.dexciuq.shoppinglist.domain.Product
import com.dexciuq.shoppinglist.domain.ProductRepository

object ProductRepositoryImpl : ProductRepository {

    private val productList = mutableListOf<Product>()
    private var autoIncrement = 0
    override fun getProductList(): List<Product> = productList.toList()

    override fun getProduct(id: Int): Product =
        productList.find {
            it.id == id
        } ?: error("Product with id: $id not found")

    override fun addProduct(product: Product) {
        if (product.id == Product.UNDEFINED_ID) {
            product.id = autoIncrement++
        }
        productList.add(product)
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
    }
}