package com.whocare.android.app.data.repos

import androidx.lifecycle.LiveData
import com.whocare.android.app.data.Result
import com.whocare.android.app.data.model.*
import com.whocare.android.app.data.source.CostDataSource

class CostRepository(private val costDataSource: CostDataSource) {
    private lateinit var _product: Product
    val getAllCosts: LiveData<List<Cost>> = costDataSource.getAllCosts
    val getAllProducts: LiveData<List<Product>> = costDataSource.getAllProducts
    val getAllCostItems: LiveData<List<CostItem>> = costDataSource.getAllCostItems
    val getAllPrices: LiveData<List<Price>> = costDataSource.getAllPrices
    val getAllCostItemCategories: LiveData<List<CostItemCategory>> = costDataSource.getAllCostItemCategories

    fun getAllCostItemDataByCostId(costId: Int): LiveData<List<CostItemData>> {
        return costDataSource.getAllCostItemDataByCostId(costId)
    }

    suspend fun add(name: String, description: String): Result<Product> {
        // handle login
        val result = costDataSource.addProduct(name, description)

        if (result is Result.Success) {
            setProduct(result.data)
        }

        return result
    }

    private fun setProduct(data: Product) {
        this._product = data
    }
}