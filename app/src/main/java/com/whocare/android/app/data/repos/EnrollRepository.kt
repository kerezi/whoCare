package com.whocare.android.app.data.repos

import androidx.lifecycle.LiveData
import com.whocare.android.app.data.source.EnrollDataSource
import com.whocare.android.app.data.Result
import com.whocare.android.app.data.model.NameId

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class EnrollRepository(val dataSource: EnrollDataSource) {
    val getAllNameIds: LiveData<List<NameId>> = dataSource.getAllNameIds

    // in-memory cache of the loggedInUser object
    var nameid: NameId? = null
        private set

    val isEnrolled: Boolean
        get() = nameid != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        nameid = null
    }

    fun leave() {
        nameid = null
        dataSource.leave()
    }

    suspend fun enroll(nameid: String, password: String): Result<NameId> {
        // handle login
        val result = dataSource.enroll(nameid, password)

        if (result is Result.Success) {
            setEnrollData(result.data)
        }

        return result
    }

    private fun setEnrollData(nameId: NameId) {
        this.nameid = nameId
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}