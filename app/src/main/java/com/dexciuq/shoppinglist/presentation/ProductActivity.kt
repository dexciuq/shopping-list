package com.dexciuq.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.shoppinglist.R
import com.dexciuq.shoppinglist.databinding.ActivityProductBinding
import com.dexciuq.shoppinglist.domain.Product

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var viewModel: ProductViewModel

    private var mode: String = ""
    private var id: Int = Product.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseIntent()
        binding = ActivityProductBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        setContentView(binding.root)

        when (mode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
        setupTextWatchers()
        setupErrorObservers()
    }

    private fun launchEditMode() {
        viewModel.getProduct(id)

        viewModel.product.observe(this) {
            binding.etName.setText(it.name)
            binding.etCount.setText(it.quantity.toString())
        }

        binding.saveButton.setOnClickListener {
            viewModel.updateProduct(
                nameInput = binding.etName.text?.toString(),
                quantityInput = binding.etCount.text?.toString()
            )
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addProduct(
                nameInput = binding.etName.text?.toString(),
                quantityInput = binding.etCount.text?.toString()
            )
        }
    }

    private fun setupErrorObservers() {
        viewModel.nameInputError.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilName.error = message
        }

        viewModel.quantityInputError.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }

        viewModel.shouldCloseScreen.observe(this) {
            finish()
        }
    }

    private fun setupTextWatchers() {
        binding.etName.doOnTextChanged { _, _, _, _ ->
            viewModel.resetNameInputError()
        }

        binding.etCount.doOnTextChanged { _, _, _, _ ->
            viewModel.resetQuantityInputError()
        }
    }


    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            error("param screen mode is absent")
        }

        mode = intent.getStringExtra(EXTRA_SCREEN_MODE).toString()
        when (mode) {
            MODE_ADD -> {}
            MODE_EDIT -> {
                if (!intent.hasExtra(EXTRA_PRODUCT_ID)) {
                    error("param product id is absent")
                }
                id = intent.getIntExtra(EXTRA_PRODUCT_ID, Product.UNDEFINED_ID)
            }
            else -> error("unknown mode")
        }

    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val EXTRA_PRODUCT_ID = "extra_product_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentAddMode(context: Context): Intent {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditMode(context: Context, id: Int): Intent {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_PRODUCT_ID, id)
            return intent
        }
    }
}