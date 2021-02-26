package com.diwixis.feature_movie_list.domain.utils

import android.content.Context
import android.widget.Toast

internal fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()