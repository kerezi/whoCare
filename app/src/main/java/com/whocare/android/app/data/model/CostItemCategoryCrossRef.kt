package com.whocare.android.app.data.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["costItemId", "costItemCategoryId"])
data class CostItemCategoryCrossRef (
    val costItemId: Int,
    val costItemCategoryId: Int
)