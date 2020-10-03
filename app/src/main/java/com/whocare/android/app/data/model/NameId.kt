package com.whocare.android.app.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class NameId(
        val userId: String,
        val displayName: String
)