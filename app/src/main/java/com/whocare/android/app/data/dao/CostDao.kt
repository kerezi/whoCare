package com.whocare.android.app.data.dao

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.whocare.android.app.data.model.*

@Dao
interface CostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCostItemCategory(costItemCategory: CostItemCategory)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPrice(price: Price)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCost(cost: Cost)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCostItem(costItem: CostItem)

    @Query("SELECT * FROM cost_item_category_table ORDER BY costItemCategoryId")
    fun getAllCostItemCategories(): LiveData<List<CostItemCategory>>

    @Query("SELECT * FROM product_table ORDER BY id")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM price_table ORDER BY id")
    fun getAllPrices(): LiveData<List<Price>>

    @Query("SELECT * FROM cost_table ORDER BY id")
    fun getAllCosts(): LiveData<List<Cost>>

    @Query("SELECT * FROM cost_item_table ORDER BY costItemId")
    fun getAllCostItems(): LiveData<List<CostItem>>

    @Transaction
    @Query("SELECT * FROM cost_item_table ORDER BY costItemId")
    fun getAllCostItemData(): LiveData<List<CostItemData>>

    @Transaction
    @Query("SELECT * FROM cost_item_table WHERE costId = :costId ORDER BY costItemId")
    fun getAllCostItemDataByCostId(costId: Int): LiveData<List<CostItemData>>
}