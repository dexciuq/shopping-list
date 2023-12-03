package com.dexciuq.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)
        setProductsRecyclerView()
        setObservers()
    }

    private fun setProductsRecyclerView() {
        adapter = ProductListAdapter()
        binding.productList.adapter = adapter
        binding.productList.recycledViewPool.setMaxRecycledViews(
            ProductListAdapter.PRODUCT_ACTIVE_VIEW_TYPE,
            ProductListAdapter.MAX_POOL_SIZE
        )
        binding.productList.recycledViewPool.setMaxRecycledViews(
            ProductListAdapter.PRODUCT_INACTIVE_VIEW_TYPE,
            ProductListAdapter.MAX_POOL_SIZE
        )
    }

    private fun setObservers() {
        viewModel.productList.observe(this) {
            adapter.productList = it
        }
    }
}