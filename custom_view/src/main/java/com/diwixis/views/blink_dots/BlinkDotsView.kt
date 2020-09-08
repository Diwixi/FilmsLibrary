package com.diwixis.views.blink_dots

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class BlinkDotsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val numberItem = DotItem().apply { doOnValueUpdateAction = { invalidate() } }
    private val paint = Paint().apply { color = Color.RED }
    private var itemWidth = 0f
    private var itemHeight = 0f

    private var lastBlink = false

    private val tick = Observable.interval(1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            lastBlink = !lastBlink
            numberItem.update(lastBlink)
        }

    private val gradientColors = intArrayOf(
        0xff3A1C71.toInt(),
        0xffD76D77.toInt(),
        0xffFFAF7B.toInt()
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        itemWidth = widthSize * 0.5f
        itemHeight = itemWidth
    }

    override fun onDraw(canvas: Canvas) {
        drawFirst(canvas)
        drawSecond(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paint.shader = LinearGradient(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            gradientColors,
            null,
            Shader.TileMode.CLAMP
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        numberItem.onDetachedFromWindow()
        tick.dispose()
    }

    private fun drawFirst(canvas: Canvas) {
        val sizeIndex = if (numberItem.value >= 0.9f) numberItem.value else 0.9f
        val left = width * 0.5f - itemWidth * 0.5f * sizeIndex
        val top = height * 0.4f - itemHeight * sizeIndex
        val right = left + itemWidth * sizeIndex
        val bot = top + itemHeight * sizeIndex

        canvas.drawRect(left, top, right, bot, paint)
    }

    private fun drawSecond(canvas: Canvas) {
        val sizeIndex = if (numberItem.value >= 0.9f) numberItem.value else 0.9f
        val left = width * 0.5f - itemWidth * 0.5f * sizeIndex
        val top = height * 0.4f + itemHeight * sizeIndex
        val right = left + itemWidth * sizeIndex
        val bot = top + itemHeight * sizeIndex

        canvas.drawRect(left, top, right, bot, paint)
    }
}