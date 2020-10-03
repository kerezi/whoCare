package com.whocare.android.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Entity(tableName = "nameid_table")
data class NameId(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val userId: String,
        val displayName: String
)