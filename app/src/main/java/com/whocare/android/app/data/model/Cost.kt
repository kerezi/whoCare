package com.whocare.android.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "cost_table")
data class Cost (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dateTime: LocalDateTime
)