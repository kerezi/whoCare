package com.whocare.android.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "cost_item_table")
data class CostItem (
    @PrimaryKey(autoGenerate = true) val costItemId: Int,
    val costId: Int,
    val productPriceId: Int,
    val ownerId: Int,
    val payerId: Int
)