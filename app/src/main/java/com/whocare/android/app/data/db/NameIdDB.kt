package com.whocare.android.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.whocare.android.app.data.dao.NameIdDao
import com.whocare.android.app.data.model.NameId

@Database(entities = [NameId::class], version = 1, exportSchema = false)
abstract class NameIdDB: RoomDatabase() {

    abstract fun nameIdDao():NameIdDao

    companion object {
        @Volatile
        private var INSTANCE: NameIdDB? = null

        fun getDB(context: Context): NameIdDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    NameIdDB::class.java,
                    "nameid_db"
                ).build()
                INSTANCE = instace
                return instace
            }
        }
    }

}