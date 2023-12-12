package com.dexciuq.shoppinglist.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.shoppinglist.R
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
        setupAddProduct()
        setObservers()
    }

    private fun isOrientationLandscape(): Boolean {
        return resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    private fun setProductContainer(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.product_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupAddProduct() {
        binding.addProductBtn.setOnClickListener {
            when (isOrientationLandscape()) {
                true -> {
                    setProductContainer(ProductFragment.newInstanceAddMode())
                }

                false -> {
                    val intent = ProductActivity.newIntentAddMode(context = this)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setProductsRecyclerView() {
        setupProductListAdapter()
        setupSwipeToDeleteCallback()

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

    private fun setupProductListAdapter() {
        adapter = ProductListAdapter()
        adapter.onProductLongClickListener = viewModel::changeEnabledState
        adapter.onProductClickListener = {
            when (isOrientationLandscape()) {
                true -> {
                    setProductContainer(ProductFragment.newInstanceEditMode(it.id))
                }

                false -> {
                    val intent = ProductActivity.newIntentEditMode(context = this, id = it.id)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupSwipeToDeleteCallback() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteProduct(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.productList)
    }

    private fun setObservers() {
        viewModel.productList.observe(this, adapter::submitList)
    }
}