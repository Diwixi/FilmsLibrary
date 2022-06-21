package com.pg.feature_network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pg.feature_network.BuildConfig.API_BASE_URL
import com.pg.remote.TmdbApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object Network {

    private val logginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

//    val networkCallback = object : ConnectivityManager.NetworkCallback() {
//        override fun onAvailable(network: Network) {
//            _networkConnection.value = Connection.AVAILABLE
//        }
//
//        override fun onLost(network: Network) {
//            _networkConnection.value = Connection.LOST
//        }
//    }

//    private val _networkConnection = MutableStateFlow(Connection.AVAILABLE)
//    val networkConnection: StateFlow<Connection> = _networkConnection.asStateFlow()

    fun client(): TmdbApi = createNetworkClient(API_BASE_URL).create(TmdbApi::class.java)

    fun createNetworkClient(baseUrl: String): Retrofit = retrofitClient(
        baseUrl,
        httpClient()
    )

    private fun httpClient() = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(logginInterceptor)
        }
    }.build()

    @OptIn(ExperimentalSerializationApi::class)
    private fun retrofitClient(
        baseUrl: String,
        httpClient: OkHttpClient
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }

    enum class Connection {
        AVAILABLE,
        LOST
    }
}