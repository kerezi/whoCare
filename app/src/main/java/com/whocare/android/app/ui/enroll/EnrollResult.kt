package com.whocare.android.app.ui.enroll

/**
 * Authentication result : success (user details) or error message.
 */
data class EnrollResult(
    val success: EnrollView? = null,
    val error: Int? = null
)