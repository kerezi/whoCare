package com.whocare.android.app.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.LocalDateTime

@Entity(tableName = "product_price_table")
data class Price (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val productId: Int,
    val dateTime: LocalDateTime,
    val price: Number
)
