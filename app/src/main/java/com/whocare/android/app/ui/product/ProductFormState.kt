package com.whocare.android.app.ui.product

/**
 * Data validation state of the login form.
 */
data class ProductFormState(
    val nameError: Int? = null,
    val descriptionError: Int? = null,
    val isDataValid: Boolean = false
)