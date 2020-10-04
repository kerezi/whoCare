package com.whocare.android.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.whocare.android.app.data.dao.CostDao
import com.whocare.android.app.data.dao.NameIdDao
import com.whocare.android.app.data.model.*

@Database(entities = [NameId::class, Product::class, CostItemCategory::class, Cost::class
    , Price::class, CostItem::class, CostItemCategoryCrossRef::class]
    , version = 1, exportSchema = false)
abstract class WhoCareDB: RoomDatabase() {

    abstract fun nameIdDao(): NameIdDao
    abstract fun costDao(): CostDao

    companion object {
        @Volatile
        private var INSTANCE: WhoCareDB? = null

        fun getDB(context: Context): WhoCareDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    WhoCareDB::class.java,
                    "whocare_db"
                ).build()
                INSTANCE = instace
                return instace
            }
        }
    }

}