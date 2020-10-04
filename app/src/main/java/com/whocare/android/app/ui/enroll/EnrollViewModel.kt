package com.whocare.android.app.ui.enroll

import android.util.Patterns
import androidx.lifecycle.*
import com.whocare.android.app.data.repos.EnrollRepository

import com.whocare.android.app.R
import com.whocare.android.app.ui.main.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class EnrollViewModel(private val enrollRepository: EnrollRepository) : ViewModel() {

    private val _enrollForm = MutableLiveData<EnrollFormState>()
    val enrollFormState: LiveData<EnrollFormState> = _enrollForm

    private val _enrollResult = MutableLiveData<Result<EnrollView>>()
    val enrollResult: LiveData<Result<EnrollView>> = _enrollResult

    fun enroll(nameid: String, password: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch(Dispatchers.IO) {
            val result = enrollRepository.enroll(nameid, password)

        }
        enrollRepository.getAllNameIds.observeForever(Observer { nameids ->
            if (nameids.any { nid -> nid.userId == nameid }) {
                _enrollResult.value = Result(success = EnrollView(displayName = nameid))
            } else {
                _enrollResult.value = Result(error = R.string.enrollment_failed)
            }

        })
    }

    fun enrollDataChanged(username: String, password: String) {
        if (!isNameIdValid(username)) {
            _enrollForm.value = EnrollFormState(nameidError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _enrollForm.value = EnrollFormState(passwordError = R.string.invalid_password)
        } else {
            _enrollForm.value = EnrollFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isNameIdValid(nameid: String): Boolean {
        return if (nameid.isNotBlank()) {
            Pattern.matches("[A-Za-z_0-9]+",nameid)
        } else {
            nameid.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}