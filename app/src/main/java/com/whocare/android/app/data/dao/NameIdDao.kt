package com.whocare.android.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whocare.android.app.data.model.NameId

@Dao
interface NameIdDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNameId(nameId: NameId)

    @Query("SELECT * FROM nameid_table ORDER BY id")
    fun getAllNameIds(): LiveData<List<NameId>>
}