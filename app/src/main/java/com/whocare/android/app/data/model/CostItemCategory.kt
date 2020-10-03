package com.whocare.android.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cost_item_category_table")
data class CostItemCategory (
    @PrimaryKey(autoGenerate = true) val costItemCategoryId: Int,
    val name: String,
    val description: String
)