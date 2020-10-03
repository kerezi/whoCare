package com.whocare.android.app.data.model

import androidx.room.Embedded
import androidx.room.Relation


data class ProductPrice(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    ) val prices: List<Price>
)