package com.whocare.android.app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.whocare.android.app.R
import com.whocare.android.app.ui.cost.list.CostFragment

class CostsActivity : AppCompatActivity() {

    lateinit var nameId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.costs_activity)
        if (intent.hasExtra("EXTRA_NAME_ID")) {
            nameId = intent.getStringExtra("EXTRA_NAME_ID").toString()
            Toast.makeText(this, "Receive: ${nameId}", Toast.LENGTH_LONG).show()
        } else {
            nameId = ""
            Toast.makeText(this, "Intent has no extra: \"EXTRA_NAME_ID\"", Toast.LENGTH_LONG).show()
        }

        setupActionBarWithNavController(findNavController(R.id.fragment))
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment, CostFragment())
//                .commitNow()
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}