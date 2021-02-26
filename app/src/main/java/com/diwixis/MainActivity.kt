package com.diwixis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.databinding.ActivityMainBinding
import com.diwixis.network.Network

internal class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val networkConnectionError = Observer<Network.Connection> { connection ->
        when (connection) {
            Network.Connection.AVAILABLE -> {
                binding.networkConnectionErrorView.isVisible = false
            }
            Network.Connection.LOST -> {
                binding.networkConnectionErrorView.isVisible = true
            }
            else -> {
                binding.networkConnectionErrorView.isVisible = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.navigation_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)

        Network.networkConnectionLiveData.observe(this, networkConnectionError)
    }
}