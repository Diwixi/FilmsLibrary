package com.diwixis.animations.number

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.diwixis.toArray

class NumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val items =
        Array(5) { Array(3) { NumberItem().apply { doOnValueUpdateAction = { invalidate() } } } }
    private val paint = Paint().apply { color = Color.RED }
    private val itemWidth
        get() = width / items[0].size.toFloat()
    private val itemHeight
        get() = height / items.size.toFloat()

    private val gradientColors = intArrayOf(
        0xff3A1C71.toInt(),
        0xffD76D77.toInt(),
        0xffFFAF7B.toInt()
    )

    override fun onDraw(canvas: Canvas) {
        items.forEachIndexed { row, rowItems ->
            rowItems.forEachIndexed { column, numberItem ->
                val left = itemWidth * column
                val top = itemHeight * row
                val right = left + itemWidth
                val bot = top + itemHeight * numberItem.value

                canvas.drawRect(left, top, right, bot, paint)
            }
        }
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

        items.forEach { it.forEach { item -> item.onDetachedFromWindow() } }
    }

    fun shareItemSize(): Pair<Float, Float> = Pair(itemWidth, itemHeight)

    fun update(data: Int) {
        val numArray = data.toArray()
        items.forEachIndexed { row, rowArray ->
            rowArray.forEachIndexed { col, numberItem ->
                numberItem.update(numArray[row][col])
            }
        }
    }
}