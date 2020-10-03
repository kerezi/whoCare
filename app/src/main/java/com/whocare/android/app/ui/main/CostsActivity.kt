package com.whocare.android.app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.whocare.android.app.R

class CostsActivity : AppCompatActivity() {

    lateinit var nameId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.costs_activity)
        nameId = intent.getStringExtra("EXTRA_NAME_ID").toString()
        Toast.makeText(this, "Receive: ${nameId}", Toast.LENGTH_LONG).show()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CostsFragment.newInstance())
                .commitNow()
        }
    }
}