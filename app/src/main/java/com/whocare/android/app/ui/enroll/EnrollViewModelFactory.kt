package com.whocare.android.app.ui.enroll

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whocare.android.app.data.EnrollDataSource
import com.whocare.android.app.data.EnrollRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class EnrollViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnrollViewModel::class.java)) {
            return EnrollViewModel(
                    enrollRepository = EnrollRepository(
                            dataSource = EnrollDataSource()
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}