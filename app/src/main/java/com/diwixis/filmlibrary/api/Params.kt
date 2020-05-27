package com.diwixis.filmlibrary.api

import com.diwixis.filmlibrary.BuildConfig
import java.util.*

/**
 * Created by Diwixis on 19.04.2017.
 */
object Params {
    val movieParams: HashMap<String, String>
        get() {
            val hashMap = HashMap<String, String>()
            hashMap["api_key"] = BuildConfig.API_KEY
            hashMap["language"] = "en"
            hashMap["page"] = "1"
            return hashMap
        }
}