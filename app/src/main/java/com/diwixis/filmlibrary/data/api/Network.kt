package com.diwixis.filmlibrary.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.diwixis.filmlibrary.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val responseBackLog = arrayListOf<Deferred<Unit>>()
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            //take action when network connection is gained
        }

        override fun onLost(network: Network) {
            //super.onLost(network)
        }
    }

    fun Deferred<Unit>.doAfterIfImposable(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gson)
        .build()
}