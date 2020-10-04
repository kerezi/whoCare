package com.whocare.android.app.data.source

import androidx.lifecycle.LiveData
import com.whocare.android.app.data.Result
import com.whocare.android.app.data.dao.CostDao
import com.whocare.android.app.data.model.*
import java.io.IOException

class CostDataSource(private val costDao: CostDao) {
    val getAllCosts: LiveData<List<Cost>> = costDao.getAllCosts()
    val getAllProducts: LiveData<List<Product>> = costDao.getAllProducts()
    val getAllPrices: LiveData<List<Price>> = costDao.getAllPrices()
    val getAllCostItems: LiveData<List<CostItem>> = costDao.getAllCostItems()
    val getAllCostItemCategories: LiveData<List<CostItemCategory>> = costDao.getAllCostItemCategories()
    fun getAllCostItemDataByCostId(costId: Int): LiveData<List<CostItemData>> {
        return costDao.getAllCostItemDataByCostId(costId)
    }

    suspend fun addProduct(name: String, description: String): Result<Product> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = Product(0, name, description)
            costDao.addProduct(fakeUser)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

}