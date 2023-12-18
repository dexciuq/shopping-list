package com.dexciuq.shoppinglist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.shoppinglist.R
import com.dexciuq.shoppinglist.databinding.FragmentProductBinding
import com.dexciuq.shoppinglist.domain.Product

class ProductFragment : Fragment() {

    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[ProductViewModel::class.java] }

    private lateinit var onSaveListener: OnSaveListener

    private var mode: String = MODE_UNKNOWN
    private var id: Int = Product.UNDEFINED_ID

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSaveListener) {
            onSaveListener = context
        } else {
            error("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreenMode()
        setupTextWatchers()
        setupErrorObservers()
    }

    private fun parseParams() {
        if (arguments?.containsKey(SCREEN_MODE) != true) {
            error("param screen mode is absent")
        }

        mode = arguments?.getString(SCREEN_MODE) ?: MODE_UNKNOWN
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            error("unknown mode")
        }

        if (mode == MODE_EDIT) {
            if (arguments?.containsKey(PRODUCT_ID) != true) {
                error("param product id is absent")
            }

            id = arguments?.getInt(PRODUCT_ID) ?: Product.UNDEFINED_ID
            if (id == Product.UNDEFINED_ID) {
                error("param product id is absent")
            }
        }
    }

    private fun setupScreenMode() {
        when (mode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getProduct(id)

        viewModel.product.observe(viewLifecycleOwner) {
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
        viewModel.nameInputError.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilName.error = message
        }

        viewModel.quantityInputError.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onSaveListener.onSaveClick()
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

    companion object {
        private const val SCREEN_MODE = "screen_mode"
        private const val PRODUCT_ID = "product_id"

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = "mode_unknown"

        fun newInstanceAddMode(): ProductFragment {
            return ProductFragment().apply {
                arguments = bundleOf(
                    SCREEN_MODE to MODE_ADD,
                )
            }
        }

        fun newInstanceEditMode(id: Int): ProductFragment {
            return ProductFragment().apply {
                arguments = bundleOf(
                    SCREEN_MODE to MODE_EDIT,
                    PRODUCT_ID to id
                )
            }
        }
    }

    interface OnSaveListener {
        fun onSaveClick()
    }
}