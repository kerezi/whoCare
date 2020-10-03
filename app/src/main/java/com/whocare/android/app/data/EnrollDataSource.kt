package com.whocare.android.app.data

import androidx.lifecycle.LiveData
import com.whocare.android.app.data.dao.NameIdDao
import com.whocare.android.app.data.model.NameId
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class EnrollDataSource(private val nameIdDao: NameIdDao) {

    val getAllNameIds: LiveData<List<NameId>> = nameIdDao.getAllNameIds()

    suspend fun enroll(nameid: String, password: String): Result<NameId> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = NameId(0, nameid,nameid.toUpperCase())
            nameIdDao.addNameId(fakeUser)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun leave() {
        // TODO: revoke authentication
    }
}