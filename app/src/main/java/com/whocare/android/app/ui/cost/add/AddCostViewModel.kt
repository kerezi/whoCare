package com.whocare.android.app.ui.cost.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.whocare.android.app.data.model.CostItemData
import com.whocare.android.app.data.model.NameId
import com.whocare.android.app.data.model.Product
import com.whocare.android.app.data.repos.CostRepository
import com.whocare.android.app.data.repos.EnrollRepository

class AddCostViewModel (private val costRepository: CostRepository, private val enrollRepository: EnrollRepository) : ViewModel() {
    val getAllNameIds: LiveData<List<NameId>> = enrollRepository.getAllNameIds

    val getAllProducts: LiveData<List<Product>> = costRepository.getAllProducts

    fun getAllCostItemDataByCostId(costId: Int): LiveData<List<CostItemData>> {
        return costRepository.getAllCostItemDataByCostId(costId)
    }

}