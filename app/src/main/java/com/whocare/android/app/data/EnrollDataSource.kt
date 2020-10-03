package com.whocare.android.app.data

import com.whocare.android.app.data.model.NameId
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class EnrollDataSource {

    fun enroll(nameid: String, password: String): Result<NameId> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = NameId(java.util.UUID.randomUUID().toString(), nameid)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}