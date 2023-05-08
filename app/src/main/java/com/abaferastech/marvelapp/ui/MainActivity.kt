package com.abaferastech.marvelapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.local.SharedPreferencesServicesImpl
import com.abaferastech.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val controller = navHostFragment.navController
        binding.bottomNavigationBar.setupWithNavController(controller)
        SharedPreferencesServicesImpl
            .initSharedPreferences(applicationContext)
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.fragmentContainerView).navigateUp()
        return true
    }

}