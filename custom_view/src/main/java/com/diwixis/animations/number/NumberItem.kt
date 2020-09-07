package com.diwixis.animations.number

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator

class NumberItem {
    private var _value: Float = 0f
    val value: Float
        get() = _value

    var isExpand = false

    private var animator: Animator? = null

    var doOnValueUpdateAction: () -> Unit = {}

    fun setValue(newValue: Float) {
        _value = newValue
        doOnValueUpdateAction()
    }

    private fun expand() {
        animate(1f)
    }

    private fun collapse() {
        animate(0f)
    }

    private fun animate(newValue: Float) {
        animator = ValueAnimator.ofFloat(value, newValue).apply {
            duration = 400L
            interpolator = AccelerateInterpolator()
            addUpdateListener { setValue(it.animatedValue as Float) }
            start()
        }

        isExpand = false
    }

    fun update(isExpand: Boolean) {
        if (isExpand == this.isExpand) return

        if (isExpand) expand() else collapse()
        this.isExpand = isExpand
    }

    fun onDetachedFromWindow() {
        animator?.end()
    }
}