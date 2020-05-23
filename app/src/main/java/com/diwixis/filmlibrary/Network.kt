package com.diwixis.filmlibrary

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun createNetworkClient(baseUrl: String) =
        retrofitClient(baseUrl, httpClient(), GsonConverterFactory.create())

    fun createNetworkClient(baseUrl: String, gson: GsonConverterFactory) =
        retrofitClient(baseUrl, httpClient(), gson)

    private fun httpClient() = OkHttpClient.Builder().build()

    private fun retrofitClient(
        baseUrl: String,
        httpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(gson)
            .build()
}