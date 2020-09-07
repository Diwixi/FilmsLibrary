package com.diwixis.animations.clock

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.diwixis.animations.number.NumberView
import com.diwixis.filmlibrary.custom_view.R
import kotlinx.android.synthetic.main.view_clock.view.*

class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.view_clock, this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val newWidth = widthSize / 5
        hours1.changeWidth(newWidth)
        hours2.changeWidth(newWidth)
        minutes1.changeWidth(newWidth)
        minutes2.changeWidth(newWidth)
    }

    fun update(num1: Int, num2: Int, num3: Int, num4: Int) {
        hours1.update(num1)
        hours2.update(num2)
        minutes1.update(num3)
        minutes2.update(num4)
    }
}

fun NumberView.changeWidth(newWidth: Int) {
    val layoutParams = layoutParams
    layoutParams.width = newWidth
    setLayoutParams(layoutParams)
    invalidate()
}