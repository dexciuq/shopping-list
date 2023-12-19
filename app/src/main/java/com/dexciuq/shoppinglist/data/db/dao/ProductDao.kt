package com.dexciuq.shoppinglist.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dexciuq.shoppinglist.data.model.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getProductList(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    suspend fun getProduct(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(productEntity: ProductEntity)

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}