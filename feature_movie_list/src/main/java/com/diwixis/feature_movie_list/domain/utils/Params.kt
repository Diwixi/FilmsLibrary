package com.diwixis.feature_movie_list.domain.utils

import com.diwixis.network.BuildConfig.API_KEY

typealias Param = HashMap<String, String>

internal object Params {
    private val movieParams: Param
        get() {
            val hashMap = Param()
            hashMap[API_KEY_TITLE] = API_KEY
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

    private const val API_KEY_TITLE = "api_key"
    private const val LANG = "language"
    private const val PAGE = "page"
}