package com.dexciuq.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.shoppinglist.R
import com.dexciuq.shoppinglist.databinding.ActivityMainBinding

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        setContentView(R.layout.activity_product)
    }

    private fun setupObservers() {
        viewModel.nameInputError.observe(this) {

        }

        viewModel.quantityInputError.observe(this) {

        }

        viewModel.product.observe(this) {

        }
    }
}