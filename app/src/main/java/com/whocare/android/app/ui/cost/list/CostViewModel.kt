package com.whocare.android.app.ui.cost.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.whocare.android.app.data.model.Cost
import com.whocare.android.app.data.repos.CostRepository

class CostViewModel(private val costRepository: CostRepository) : ViewModel() {
    val getAllCosts: LiveData<List<Cost>> = costRepository.getAllCosts
}