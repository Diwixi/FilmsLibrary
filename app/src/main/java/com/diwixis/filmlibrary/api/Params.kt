package com.diwixis.filmlibrary.api

import com.diwixis.filmlibrary.api.Params.API_KEY
import com.pg.network.BuildConfig

typealias Param = HashMap<String, String>

object Params {
    private val movieParams: Param
        get() {
            val hashMap = Param()
            hashMap[API_KEY] = BuildConfig.API_KEY
            hashMap[LANG] = "en"
            hashMap[PAGE] = "1"
            return hashMap
        }

    fun getParams(page: Int): Param {
        return if (page == 1) {
            movieParams
        } else {
            movieParams.apply { this[PAGE] = page.toString() }
        }
    }

    private const val API_KEY = "api_key"
    private const val LANG = "language"
    private const val PAGE = "page"
}