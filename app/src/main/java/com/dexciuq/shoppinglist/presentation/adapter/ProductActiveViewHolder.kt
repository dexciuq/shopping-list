package com.dexciuq.shoppinglist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.shoppinglist.databinding.ItemProductActiveBinding
import com.dexciuq.shoppinglist.domain.model.Product

class ProductActiveViewHolder(
    private val binding: ItemProductActiveBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        product: Product,
        onProductClickListener: (Product) -> Unit = {},
        onProductLongClickListener: (Product) -> Unit = {},
    ) {
        binding.productName.text = product.name
        binding.productQuantity.text = product.quantity.toString()
        itemView.setOnLongClickListener {
            onProductLongClickListener(product)
            true
        }
        itemView.setOnClickListener {
            onProductClickListener(product)
        }
    }
}