package com.diwixis.views.clock

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.diwixis.filmlibrary.custom_view.R
import kotlinx.android.synthetic.main.view_clock.view.*
import java.util.*

internal class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val calendar: Calendar
        get() = Calendar.getInstance()

    init {
        inflate(context, R.layout.view_clock, this)
        showTime()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val newWidth = widthSize / 5
        hours1.changeWidth(newWidth)
        hours2.changeWidth(newWidth)
        minutes1.changeWidth(newWidth)
        minutes2.changeWidth(newWidth)
    }

    private fun getTime(): List<Int> {
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val timeList = if (hours < 10) {
            mutableListOf(0, hours)
        } else {
            mutableListOf(hours / 10, hours % 10)
        }
        val minutes = calendar.get(Calendar.MINUTE)
        if (minutes < 10) {
            timeList.addAll(listOf(0, minutes))
        } else {
            timeList.addAll(listOf(minutes / 10, minutes % 10))
        }
        return timeList
    }

    private fun showTime() {
        val timeList = getTime()
        update(timeList[0], timeList[1], timeList[2], timeList[3])
    }

    private fun update(num1: Int, num2: Int, num3: Int, num4: Int) {
        hours1.update(num1)
        hours2.update(num2)
        minutes1.update(num3)
        minutes2.update(num4)
    }
}