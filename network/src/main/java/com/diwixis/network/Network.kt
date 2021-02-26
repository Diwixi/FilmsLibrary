package com.diwixis.network

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            networkConnection.postValue(Connection.AVAILABLE)
        }

        override fun onLost(network: Network) {
            networkConnection.postValue(Connection.LOST)
        }
    }

    private val networkConnection: MutableLiveData<Connection> = MutableLiveData()
    val networkConnectionLiveData: LiveData<Connection> = networkConnection

    fun createNetworkClient(baseUrl: String): Retrofit = retrofitClient(
        baseUrl,
        httpClient(),
        GsonConverterFactory.create()
    )

    fun createNetworkClient(baseUrl: String, gson: GsonConverterFactory): Retrofit = retrofitClient(
        baseUrl,
        httpClient(),
        gson
    )

    private fun httpClient() = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(logginInterceptor)
        }
    }.build()

    private fun retrofitClient(
        baseUrl: String,
        httpClient: OkHttpClient,
        gson: GsonConverterFactory
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(gson)
        .build()

    enum class Connection {
        AVAILABLE,
        LOST
    }
}