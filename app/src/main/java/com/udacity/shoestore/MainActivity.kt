package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        navController = this.findNavController(R.id.myNavHostFragment)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.loginFragment, R.id.instructionsFragment, R.id.welcomeFragment, R.id.shoeListingFragment).build()

        // link the nav controller to the action bar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logoutItem -> navigateToLoginScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLoginScreen() {
        if (navController.currentDestination?.id == R.id.shoeDetailFragment) {
            val navOptions = NavOptions.Builder().setLaunchSingleTop(true).setPopUpTo(R.id.shoeListingFragment, true).build()
            navController.navigate(R.id.loginFragment, null, navOptions)
        } else {
            navController.navigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
