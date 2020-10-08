package com.diwixis.filmlibrary

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.diwixis.filmlibrary.data.api.Network
import com.diwixis.filmlibrary.domain.di.dataModule
import com.diwixis.filmlibrary.domain.di.repositoryModule
import com.diwixis.filmlibrary.domain.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FilmsLibraryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FilmsLibraryApp)
            modules(dataModule, repositoryModule, viewModelModule)
        }
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            registerNetworkCallback(NetworkRequest.Builder().build(), Network.networkCallback)
        }
    }
}