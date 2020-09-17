package com.diwixis.views.equalizer

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import kotlin.math.abs

internal class BarItem {
    private var _value: Float = 0f
    val value: Float
        get() = _value

    private var animator: Animator? = null

    var doOnValueUpdateAction: (Float) -> Unit = {}

    fun setValue(newValue: Float) {
        _value = newValue
        doOnValueUpdateAction(newValue)
    }

    fun updateValueRelatively(delta: Float) {
        if (abs(delta) < 0.1 || animator?.isRunning == true) return

        val newValue = (value + delta).coerceIn(0f, 1f)

        animator = ValueAnimator.ofFloat(value, newValue).apply {
            duration = ((abs(delta) * 400L).toLong())
            interpolator = DecelerateInterpolator()
            addUpdateListener { setValue(it.animatedValue as Float) }
            doOnEnd { collapseBar(400L - duration) }
            start()
        }
    }

    private fun collapseBar(delay: Long) {
        animator = ValueAnimator.ofFloat(value, 0f).apply {
            startDelay = delay
            duration = ((abs(value) * 1200L).toLong())
            interpolator = AccelerateInterpolator()
            addUpdateListener { setValue(it.animatedValue as Float) }
            start()
        }
    }

    fun onDetachedFromWindow() {
        animator?.end()
    }
}