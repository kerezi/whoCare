package com.whocare.android.app.ui.enroll

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whocare.android.app.data.source.EnrollDataSource
import com.whocare.android.app.data.repos.EnrollRepository
import com.whocare.android.app.data.dao.NameIdDao
import com.whocare.android.app.data.db.NameIdDB

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class EnrollViewModelFactory(app: Application) : ViewModelProvider.Factory {

    private val nameIdDao: NameIdDao

    init {
        nameIdDao = NameIdDB.getDB(app).nameIdDao()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnrollViewModel::class.java)) {
            return EnrollViewModel(
                    enrollRepository = EnrollRepository(
                            dataSource = EnrollDataSource(nameIdDao)
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}