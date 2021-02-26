package com.diwixis.network

import com.diwixis.network.Constants.Companion.API_BASE_URL
import retrofit2.Retrofit

val appRetrofit: Retrofit
    get() = Network.createNetworkClient(API_BASE_URL)