package com.diwixis.filmlibrary.domain.utils

import android.view.View

class DebounceOnClickListener(
    private val intervalMillis: Long,
    private val doClick: ((View) -> Unit)
) : View.OnClickListener {

    override fun onClick(v: View) {
        if (enabled) {
            enabled = false
            v.postDelayed(ENABLE_AGAIN, intervalMillis)
            doClick(v)
        }
    }

    companion object {
        @JvmStatic
        var enabled = true
        private val ENABLE_AGAIN = Runnable { enabled = true }
    }
}

fun View.setOnClick(intervalMillis: Long = 0, doClick: (View) -> Unit) = setOnClickListener(
    DebounceOnClickListener(intervalMillis = intervalMillis, doClick = doClick)
)