package com.dexciuq.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dexciuq.shoppinglist.R
import com.dexciuq.shoppinglist.domain.Product

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    var productList = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        val product = productList[position]
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

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun getItemViewType(position: Int): Int {
        val product = productList[position]
        return when (product.active) {
            true -> PRODUCT_ACTIVE_VIEW_TYPE
            false -> PRODUCT_INACTIVE_VIEW_TYPE
        }
    }

    class ProductViewHolder(view: View) : ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.product_name)
        val quantity: TextView = view.findViewById(R.id.product_quantity)
    }

    companion object {
        const val PRODUCT_ACTIVE_VIEW_TYPE = 0
        const val PRODUCT_INACTIVE_VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 15
    }
}