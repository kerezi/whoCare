package com.whocare.android.app.ui.main

/**
 * Authentication result : success (user details) or error message.
 */
data class Result<T>(
    val success: T? = null,
    val error: Int? = null
)