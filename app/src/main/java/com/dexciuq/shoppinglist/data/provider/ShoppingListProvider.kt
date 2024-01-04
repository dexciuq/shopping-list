package com.dexciuq.shoppinglist.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dexciuq.shoppinglist.applicationComponent
import com.dexciuq.shoppinglist.data.db.dao.ProductDao
import com.dexciuq.shoppinglist.data.mapper.ProductMapper
import com.dexciuq.shoppinglist.domain.model.Product
import javax.inject.Inject

class ShoppingListProvider : ContentProvider() {

    @Inject
    lateinit var productDao: ProductDao

    @Inject
    lateinit var mapper: ProductMapper

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.dexciuq.shoppinglist", "products", CODE_QUERY_GET_PRODUCTS)
        addURI("com.dexciuq.shoppinglist", "products/#", CODE_QUERY_GET_PRODUCT_BY_ID)
    }

    override fun onCreate(): Boolean {
        context?.applicationComponent?.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            CODE_QUERY_GET_PRODUCTS -> productDao.getProductListCursor()
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            CODE_QUERY_GET_PRODUCTS -> {
                values ?: return null
                val id = values.getAsInteger("id")
                val name = values.getAsString("name")
                val quantity = values.getAsDouble("quantity")
                val active = values.getAsBoolean("active")
                val product = Product(
                    id = id,
                    name = name,
                    quantity = quantity,
                    active = active
                )
                productDao.addProductSync(product.run(mapper::toEntity))
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val CODE_QUERY_GET_PRODUCTS = 100
        private const val CODE_QUERY_GET_PRODUCT_BY_ID = 101
    }
}