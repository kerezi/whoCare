package com.whocare.android.app.ui.enroll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.whocare.android.app.data.EnrollRepository
import com.whocare.android.app.data.Result

import com.whocare.android.app.R

class EnrollViewModel(private val enrollRepository: EnrollRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<EnrollFormState>()
    val enrollFormState: LiveData<EnrollFormState> = _loginForm

    private val _loginResult = MutableLiveData<EnrollResult>()
    val enrollResult: LiveData<EnrollResult> = _loginResult

    fun enroll(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = enrollRepository.enroll(username, password)

        if (result is Result.Success) {
            _loginResult.value = EnrollResult(success = EnrollView(displayName = result.data.displayName))
        } else {
            _loginResult.value = EnrollResult(error = R.string.login_failed)
        }
    }

    fun enrollDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = EnrollFormState(nameidError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = EnrollFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = EnrollFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}