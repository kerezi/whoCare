package com.whocare.android.app.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope

import com.whocare.android.app.R
import com.whocare.android.app.data.repos.CostRepository
import com.whocare.android.app.ui.main.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ProductViewModel(private val costRepository: CostRepository) : ViewModel() {

    private val _productForm = MutableLiveData<ProductFormState>()
    val productFormState: LiveData<ProductFormState> = _productForm

    private val _productResult = MutableLiveData<Result<ProductView>>()
    val productResult: LiveData<Result<ProductView>> = _productResult

    fun add(name: String, description: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch(Dispatchers.IO) {
            val result = costRepository.add(name, description)
        }

        costRepository.getAllProducts.observeForever(Observer { products ->
            if (products.any { p -> p.name == name }) {
                _productResult.value = Result(success = ProductView(displayName = name))
            } else {
                _productResult.value = Result(error = R.string.add_product_failed)
            }

        })

    }

    fun productDataChanged(username: String, password: String) {
        if (!isNameValid(username)) {
            _productForm.value = ProductFormState(nameError = R.string.invalid_username)
        } else if (!isDescriptionValid(password)) {
            _productForm.value = ProductFormState(descriptionError = R.string.invalid_description)
        } else {
            _productForm.value = ProductFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isNameValid(name: String): Boolean {
        return if (name.isNotBlank()) {
            Pattern.matches("[A-Za-z_0-9]+",name)
        } else {
            name.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isDescriptionValid(password: String): Boolean {
        return password.length > 5
    }
}