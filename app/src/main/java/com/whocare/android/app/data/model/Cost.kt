package com.whocare.android.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cost_table")
data class Cost (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dateTime: String
): Parcelable