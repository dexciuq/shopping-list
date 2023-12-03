package com.dexciuq.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.shoppinglist.R

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.product_name)
    val quantity: TextView = view.findViewById(R.id.product_quantity)
}