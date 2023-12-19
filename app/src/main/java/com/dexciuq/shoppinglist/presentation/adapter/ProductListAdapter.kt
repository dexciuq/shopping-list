package com.dexciuq.shoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dexciuq.shoppinglist.databinding.ItemProductActiveBinding
import com.dexciuq.shoppinglist.databinding.ItemProductInactiveBinding
import com.dexciuq.shoppinglist.domain.model.Product

class ProductListAdapter : ListAdapter<Product, ViewHolder>(ProductDiffCallback()) {

    var onProductLongClickListener: (Product) -> Unit = {}
    var onProductClickListener: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            PRODUCT_ACTIVE_VIEW_TYPE -> {
                ProductActiveViewHolder(
                    ItemProductActiveBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            PRODUCT_INACTIVE_VIEW_TYPE -> {
                ProductInactiveViewHolder(
                    ItemProductInactiveBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> error("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)

        when (holder) {
            is ProductActiveViewHolder -> {
                holder.bind(
                    product, onProductClickListener, onProductLongClickListener
                )
            }

            is ProductInactiveViewHolder -> {
                holder.bind(
                    product, onProductClickListener, onProductLongClickListener
                )
            }
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