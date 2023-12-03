package com.dexciuq.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dexciuq.shoppinglist.R
import com.dexciuq.shoppinglist.domain.Product

class ProductListAdapter : ListAdapter<Product, ProductViewHolder>(ProductDiffCallback()) {

    var onProductLongClickListener: (Product) -> Unit = {}
    var onProductClickListener: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = when (viewType) {
            PRODUCT_ACTIVE_VIEW_TYPE -> R.layout.item_product_active
            PRODUCT_INACTIVE_VIEW_TYPE -> R.layout.item_product_inactive
            else -> error("unknown view type")
        }
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        holder.name.text = product.name
        holder.quantity.text = product.quantity.toString()

        holder.itemView.setOnLongClickListener {
            onProductLongClickListener(product)
            true
        }
        holder.itemView.setOnClickListener {
            onProductClickListener(product)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val product = getItem(position)
        return when (product.active) {
            true -> PRODUCT_ACTIVE_VIEW_TYPE
            false -> PRODUCT_INACTIVE_VIEW_TYPE
        }
    }

    companion object {
        const val PRODUCT_ACTIVE_VIEW_TYPE = 0
        const val PRODUCT_INACTIVE_VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 15
    }
}