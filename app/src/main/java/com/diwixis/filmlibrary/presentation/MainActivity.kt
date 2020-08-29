package com.diwixis.filmlibrary.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.diwixis.filmlibrary.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.get

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = findViewById(R.id.bottomNav)

        val navController = findNavController(R.id.navHostFragment)
        navView.setupWithNavController(navController)
    }
}