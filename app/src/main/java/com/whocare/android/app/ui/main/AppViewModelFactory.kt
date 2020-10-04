package com.whocare.android.app.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whocare.android.app.data.dao.CostDao
import com.whocare.android.app.data.source.EnrollDataSource
import com.whocare.android.app.data.repos.EnrollRepository
import com.whocare.android.app.data.dao.NameIdDao
import com.whocare.android.app.data.db.WhoCareDB
import com.whocare.android.app.data.repos.CostRepository
import com.whocare.android.app.data.source.CostDataSource
import com.whocare.android.app.ui.cost.add.AddCostViewModel
import com.whocare.android.app.ui.cost.list.CostViewModel
import com.whocare.android.app.ui.enroll.EnrollViewModel
import com.whocare.android.app.ui.product.ProductViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class AppViewModelFactory(app: Application) : ViewModelProvider.Factory {

    private val nameIdDao: NameIdDao
    private val costDao: CostDao

    init {
        val db = WhoCareDB.getDB(app)
        nameIdDao = db.nameIdDao()
        costDao = db.costDao()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnrollViewModel::class.java)) {
            return EnrollViewModel(
                    enrollRepository = EnrollRepository(
                            dataSource = EnrollDataSource(nameIdDao)
                    )
            ) as T
        } else if (modelClass.isAssignableFrom(AddCostViewModel::class.java)) {
            return AddCostViewModel(
                costRepository = CostRepository(
                    costDataSource = CostDataSource(costDao)
                ),
                enrollRepository = EnrollRepository(
                    dataSource = EnrollDataSource(nameIdDao)
                )
            ) as T
        }else if (modelClass.isAssignableFrom(CostViewModel::class.java)) {

            return CostViewModel(
                costRepository = CostRepository(
                    costDataSource = CostDataSource(costDao)
                )
            ) as T
        }
        else if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {

            return ProductViewModel(
                costRepository = CostRepository(
                    costDataSource = CostDataSource(costDao)
                )
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}