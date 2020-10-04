package com.whocare.android.app.ui.product

import android.app.Application
import androidx.lifecycle.Observer
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.whocare.android.app.R
import com.whocare.android.app.ui.main.AppViewModelFactory

class ProductFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = ViewModelProvider(this, AppViewModelFactory(activity?.application as Application))
            .get(ProductViewModel::class.java)

        val nameEditText = view.findViewById<EditText>(R.id.name)
        val descriptionEditText = view.findViewById<EditText>(R.id.description)
        val addButton = view.findViewById<Button>(R.id.add)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        productViewModel.productFormState.observe(viewLifecycleOwner,
            Observer { productFormState ->
                if (productFormState == null) {
                    return@Observer
                }
                addButton.isEnabled = productFormState.isDataValid
                productFormState.nameError?.let {
                    nameEditText.error = getString(it)
                }
                productFormState.descriptionError?.let {
                    descriptionEditText.error = getString(it)
                }
            })

        productViewModel.productResult.observe(viewLifecycleOwner,
            Observer { productResult ->
                productResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                productResult.error?.let {
                    showAddProductFailed(it)
                }
                productResult.success?.let {
                    updateUiWithProduct(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                productViewModel.productDataChanged(
                    nameEditText.text.toString(),
                    descriptionEditText.text.toString()
                )
            }
        }
        nameEditText.addTextChangedListener(afterTextChangedListener)
        descriptionEditText.addTextChangedListener(afterTextChangedListener)
        descriptionEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                productViewModel.add(
                    nameEditText.text.toString(),
                    descriptionEditText.text.toString()
                )
            }
            false
        }

        addButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            productViewModel.add(
                nameEditText.text.toString(),
                descriptionEditText.text.toString()
            )
            findNavController().navigate(R.id.action_productFragment_to_addCostFragment)
        }
    }

    private fun updateUiWithProduct(model: ProductView) {
        val welcome = "${getString(R.string.action_add)} ${model.displayName}"
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showAddProductFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}