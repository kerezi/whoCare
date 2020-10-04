package com.whocare.android.app.ui.enroll

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.whocare.android.app.R
import com.whocare.android.app.ui.main.AppViewModelFactory
import com.whocare.android.app.ui.main.CostsActivity


class EnrollActivity : AppCompatActivity() {

    private lateinit var enrollViewModel: EnrollViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_enroll)

        val nameid = findViewById<EditText>(R.id.nameid)
        val password = findViewById<EditText>(R.id.description)
        val enroll = findViewById<Button>(R.id.enroll)
        val loading = findViewById<ProgressBar>(R.id.loading)

        enrollViewModel = ViewModelProvider(this, AppViewModelFactory(application)).get(EnrollViewModel::class.java)

        enrollViewModel.enrollFormState.observe(this@EnrollActivity, Observer {
            val enrollState = it ?: return@Observer

            // disable login button unless both username / password is valid
            enroll.isEnabled = enrollState.isDataValid

            if (enrollState.nameidError != null) {
                nameid.error = getString(enrollState.nameidError)
            }
            if (enrollState.passwordError != null) {
                password.error = getString(enrollState.passwordError)
            }
        })

        enrollViewModel.enrollResult.observe(this@EnrollActivity, Observer {
            val enrollResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (enrollResult.error != null) {
                showEnrollFailed(enrollResult.error)
            }
            if (enrollResult.success != null) {
                updateUiWithUser(enrollResult.success)

                val i = Intent(baseContext, CostsActivity::class.java)
                i.putExtra("EXTRA_NAME_ID", nameid.text.toString())
                startActivity(i)                //Complete and destroy login activity once successful

                setResult(Activity.RESULT_OK)
                finish()
            }

        })

        nameid.afterTextChanged {
            enrollViewModel.enrollDataChanged(
                nameid.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                enrollViewModel.enrollDataChanged(
                    nameid.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        enrollViewModel.enroll(
                            nameid.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            enroll.setOnClickListener {
                loading.visibility = View.VISIBLE
                enrollViewModel.enroll(nameid.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser(model: EnrollView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showEnrollFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}