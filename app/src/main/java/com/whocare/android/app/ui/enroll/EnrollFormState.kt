package com.whocare.android.app.ui.enroll

/**
 * Data validation state of the login form.
 */
data class EnrollFormState(val nameidError: Int? = null,
                           val passwordError: Int? = null,
                           val isDataValid: Boolean = false)