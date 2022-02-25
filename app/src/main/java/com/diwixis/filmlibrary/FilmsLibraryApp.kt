package com.diwixis.filmlibrary

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.diwixis.filmlibrary.domain.di.initApp
import com.pg.network.Network

class FilmsLibraryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initApp(this)
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            registerNetworkCallback(NetworkRequest.Builder().build(), Network.networkCallback)
        }
    }
}