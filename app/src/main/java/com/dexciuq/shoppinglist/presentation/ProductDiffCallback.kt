package com.dexciuq.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.dexciuq.shoppinglist.domain.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}