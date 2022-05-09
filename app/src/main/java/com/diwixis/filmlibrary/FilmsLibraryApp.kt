package com.diwixis.filmlibrary

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.diwixis.filmlibrary.domain.di.sourceModule
import com.diwixis.filmlibrary.domain.di.useCaseModule
import com.diwixis.filmlibrary.domain.di.viewModelModule
import com.pg.network.Network
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class FilmsLibraryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@FilmsLibraryApp)
            modules(viewModelModule, sourceModule, useCaseModule)
        }

        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            registerNetworkCallback(NetworkRequest.Builder().build(), Network.networkCallback)
        }
    }
}