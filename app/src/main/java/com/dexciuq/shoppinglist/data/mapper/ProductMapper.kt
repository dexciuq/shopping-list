package com.dexciuq.shoppinglist.data.mapper

import com.dexciuq.shoppinglist.data.model.ProductEntity
import com.dexciuq.shoppinglist.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toEntity(product: Product): ProductEntity = ProductEntity(
        id = product.id,
        name = product.name,
        quantity = product.quantity,
        active = product.active
    )

    fun toDomain(productEntity: ProductEntity): Product = Product(
        id = productEntity.id,
        name = productEntity.name,
        quantity = productEntity.quantity,
        active = productEntity.active,
    )

    fun toEntity(productList: List<Product>): List<ProductEntity> =
        productList.map(::toEntity)

    fun toDomain(productEntityList: List<ProductEntity>): List<Product> =
        productEntityList.map(::toDomain)
}