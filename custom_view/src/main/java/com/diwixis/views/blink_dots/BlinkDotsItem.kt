package com.diwixis.views.blink_dots

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd

internal class BlinkDotsItem {
    private var _value: Float = 0f
    val value: Float
        get() = _value

    private var animator: Animator? = null

    var doOnValueUpdateAction: () -> Unit = {}

    fun setValue(newValue: Float) {
        _value = newValue
        doOnValueUpdateAction()
    }

    private fun blinkStart() {
        animator = ValueAnimator.ofFloat(value, 1f).apply {
            duration = 400L
            interpolator = DecelerateInterpolator()
            addUpdateListener { setValue(it.animatedValue as Float) }
            doOnEnd { blinkEnd() }
            start()
        }
    }

    private fun blinkEnd() {
        animator = ValueAnimator.ofFloat(value, 0.9f).apply {
            duration = 600L
            interpolator = AccelerateInterpolator()
            addUpdateListener { setValue(it.animatedValue as Float) }
            doOnEnd { blinkStart() }
            start()
        }
    }

    fun start() {
        blinkStart()
    }

    fun onDetachedFromWindow() {
        animator?.end()
    }
}