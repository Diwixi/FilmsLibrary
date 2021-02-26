package com.diwixis

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.diwixis.feature_movie_list.domain.di.injectMovieList
import com.diwixis.network.Network
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class FilmsLibraryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FilmsLibraryApp)
        }
        injectMovieList()

        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            registerNetworkCallback(NetworkRequest.Builder().build(), Network.networkCallback)
        }
    }
}