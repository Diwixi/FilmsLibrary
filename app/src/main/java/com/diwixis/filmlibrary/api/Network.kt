package com.diwixis.filmlibrary.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun createNetworkClient(baseUrl: String) =
        retrofitClient(
            baseUrl,
            httpClient(),
            GsonConverterFactory.create()
        )

    fun createNetworkClient(baseUrl: String, gson: GsonConverterFactory) =
        retrofitClient(
            baseUrl,
            httpClient(),
            gson
        )

    private fun httpClient() =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            //TODO use only for DEBUG
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

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