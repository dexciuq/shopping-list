package com.dexciuq.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dexciuq.shoppinglist.R
import com.dexciuq.shoppinglist.databinding.ActivityProductBinding
import com.dexciuq.shoppinglist.domain.model.Product

class ProductActivity : AppCompatActivity(), ProductFragment.OnSaveListener {

    private val binding by lazy { ActivityProductBinding.inflate(layoutInflater) }
    private var mode: String = MODE_UNKNOWN
    private var id: Int = Product.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightFragment()
        }
    }

    override fun onSaveClick() {
        Toast.makeText(
            this@ProductActivity,
            "Success",
            Toast.LENGTH_SHORT
        ).show()
        finish()
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

    private fun launchRightFragment() {
        val fragment = when (mode) {
            MODE_ADD -> ProductFragment.newInstanceAddMode()
            MODE_EDIT -> ProductFragment.newInstanceEditMode(id)
            else -> error("unknown mode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.product_container, fragment)
            .commit()
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val EXTRA_PRODUCT_ID = "extra_product_id"

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = "mode_unknown"

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