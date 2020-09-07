package com.diwixis.animations.equalizer

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.lang.IllegalStateException

class EqualizerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var bars = emptyList<BarItem>()
    private val paint = Paint().apply { color = Color.RED }
    private val path = Path()
    private val gradientColors = intArrayOf(
        0xff3A1C71.toInt(),
        0xffD76D77.toInt(),
        0xffFFAF7B.toInt()
    )
    private val gap
        get() = height * 0.02f
    private val barWidth
        get() = (width - gap * (bars.size - 1)) / bars.size.toFloat()
    private val center
        get() = height * 0.5f

    fun init(barsCount: Int) {
        bars = (0 until barsCount).map {
            BarItem().apply {
                doOnValueUpdateAction = { invalidate() }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        path.rewind()
        bars.forEachIndexed { index, barItem ->
            val left = (barWidth + gap) * index
            val barHeightHalf = (center * barItem.value).coerceAtLeast(barWidth)
            path.addRoundRect(
                left,
                center - barHeightHalf,
                left + barWidth,
                center + barHeightHalf,
                barWidth * 0.5f,
                barWidth * 0.5f,
                Path.Direction.CCW
            )
        }
        canvas.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paint.shader = LinearGradient(
            0f,
            center,
            0f,
            0f,
            gradientColors,
            null,
            Shader.TileMode.CLAMP
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        bars.forEach { bar -> bar.onDetachedFromWindow() }
    }

    fun updateBarsRelatively(data: List<Float>) {
        if (data.size != bars.size) throw IllegalStateException()

        bars.forEachIndexed { index, barItem ->
            barItem.updateValueRelatively(data[index])
        }
    }
}