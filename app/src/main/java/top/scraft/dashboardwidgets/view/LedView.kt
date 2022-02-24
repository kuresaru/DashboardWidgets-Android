package top.scraft.dashboardwidgets.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.ColorUtils
import kotlin.math.max
import kotlin.math.min

class LedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontMetricsInt = paint.fontMetricsInt

    var hue = 120F
        set(value) {
            field = value
            updateColor()
        }

    var brightness = 1F
        set(value) {
            field = value
            updateColor()
        }

    private var color = Color.TRANSPARENT

    init {
        updateColor()
    }

    private fun updateColor() {
        val saturation = 1F
        val lightness = max(0.15F, brightness / 2F)
        color = ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val x = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val y = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val sz = min(x, y)
        setMeasuredDimension(sz, sz)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        paint.color = color
        canvas.drawCircle(w / 2F, h / 2F, w / 2F, paint)
    }
}