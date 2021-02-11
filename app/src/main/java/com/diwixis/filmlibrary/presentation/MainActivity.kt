package com.diwixis.filmlibrary.presentation

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.data.api.Network
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val networkConnectionError = Observer<Network.Connection> { connection ->
        when (connection) {
            Network.Connection.AVAILABLE -> {
                networkConnectionErrorView.isVisible = false
            }
            Network.Connection.LOST -> {
                networkConnectionErrorView.isVisible = true
            }
            else -> {
                networkConnectionErrorView.isVisible = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = findViewById(R.id.bottomNav)

        val navController = findNavController(R.id.navHostFragment)
        navView.setupWithNavController(navController)

        Network.networkConnectionLiveData.observe(this, networkConnectionError)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages = rawMessages.map { it as NdefMessage }
                messages.toString()
                // Обработка массива сообщений.
            }
        }
    }
}