package com.diwixis.filmlibrary.domain.utils

import com.diwixis.filmlibrary.BuildConfig
import kotlin.collections.HashMap

typealias Param = HashMap<String, String>

object Params {
    val movieParams: Param
        get() {
            val hashMap = Param()
            hashMap[API_KEY] = BuildConfig.API_KEY
            hashMap[LANG] = "en"
            hashMap[PAGE] = "1"
            return hashMap
        }

    fun getParamsWithPage(page: Int): Param {
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