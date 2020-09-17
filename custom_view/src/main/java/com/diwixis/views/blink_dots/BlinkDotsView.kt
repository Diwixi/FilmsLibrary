package com.diwixis.views.blink_dots

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

internal class BlinkDotsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val blinkItem = BlinkDotsItem().apply { doOnValueUpdateAction = { invalidate() } }
    private val paint = Paint().apply { color = Color.RED }
    private var itemWidth = 0f
    private var itemHeight = 0f

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
        blinkItem.start()
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
        blinkItem.onDetachedFromWindow()
    }

    private fun drawFirst(canvas: Canvas) {
        val left = width * 0.5f - itemWidth * 0.5f * blinkItem.value
        val top = height * 0.4f - itemHeight * blinkItem.value
        val right = left + itemWidth * blinkItem.value
        val bot = top + itemHeight * blinkItem.value

        canvas.drawRect(left, top, right, bot, paint)
    }

    private fun drawSecond(canvas: Canvas) {
        val left = width * 0.5f - itemWidth * 0.5f * blinkItem.value
        val top = height * 0.4f + itemHeight * blinkItem.value
        val right = left + itemWidth * blinkItem.value
        val bot = top + itemHeight * blinkItem.value

        canvas.drawRect(left, top, right, bot, paint)
    }
}