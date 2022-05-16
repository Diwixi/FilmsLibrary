package com.diwixis.filmlibrary.api

import com.pg.network.BuildConfig

typealias Param = HashMap<String, String>

object Params {
    val movieParams: Param
        get() {
            val hashMap = Param()
            hashMap[API_KEY] = BuildConfig.API_KEY
            hashMap[LANG] = "en"
            return hashMap
        }

    private const val API_KEY = "api_key"
    private const val LANG = "language"
}