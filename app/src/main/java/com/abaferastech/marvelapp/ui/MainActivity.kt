package com.abaferastech.marvelapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val controller = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigationBar, controller)

        binding.bottomNavigationBar.setOnItemReselectedListener { item ->
            val reselectedDestinationId = item.itemId
            controller.popBackStack(reselectedDestinationId, true)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(controller.graph)
        setupActionBarWithNavController(controller, appBarConfiguration)

    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("MyActivity", "onOptionsItemSelected called with itemId ${item.itemId}")
        val navOptions = NavOptions.Builder()
            .setPopUpTo(item.itemId, false)
            .build()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val controller = navHostFragment.navController

        when(item.itemId) {
            R.id.home -> {
                Log.i("cak","fasdfa")
                controller.navigate(R.id.homeFragment, null, navOptions)
                return true
            }
            R.id.search -> {
                controller.navigate(R.id.searchFragment, null, navOptions)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }*/


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)
    }
}