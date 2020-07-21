package com.diwixis.filmlibrary

import android.view.View

/**
 * for debouncing on click
 *
 * @author П. Густокашин (Diwixis)
 */
class DebouncingOnClickListener(
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

fun View.setOnClick(intervalMillis: Long = 0, doClick: (View) -> Unit) =
    setOnClickListener(
        DebouncingOnClickListener(
            intervalMillis = intervalMillis,
            doClick = doClick
        )
    )