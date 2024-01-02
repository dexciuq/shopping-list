package com.dexciuq.shoppinglist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dexciuq.shoppinglist.data.db.dao.ProductDao
import com.dexciuq.shoppinglist.data.mapper.ProductMapper
import com.dexciuq.shoppinglist.domain.model.Product
import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productMapper: ProductMapper,
) : ProductRepository {
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