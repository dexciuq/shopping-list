package com.dexciuq.shoppinglist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dexciuq.shoppinglist.data.db.AppDatabase
import com.dexciuq.shoppinglist.data.db.dao.ProductDao
import com.dexciuq.shoppinglist.data.mapper.ProductMapper
import com.dexciuq.shoppinglist.domain.model.Product
import com.dexciuq.shoppinglist.domain.repository.ProductRepository

class ProductRepositoryImpl(
    application: Application
) : ProductRepository {

    private val productDao: ProductDao = AppDatabase.getInstance(application).productDao()
    private val productMapper: ProductMapper = ProductMapper()

    override fun getProductList(): LiveData<List<Product>> =
        productDao.getProductList().map {
            it.run(productMapper::toDomain)
        }

    override suspend fun getProduct(id: Int): Product =
        productDao.getProduct(id).run(productMapper::toDomain)

    override suspend fun addProduct(product: Product) {
        productDao.addProduct(
            product.run(productMapper::toEntity)
        )
    }

    override suspend fun updateProduct(product: Product) {
        productDao.addProduct(
            product.run(productMapper::toEntity)
        )
    }

    override suspend fun deleteProduct(id: Int) {
        productDao.deleteProduct(id)
    }
}