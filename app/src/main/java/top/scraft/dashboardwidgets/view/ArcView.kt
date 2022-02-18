package top.scraft.dashboardwidgets.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.math.min

class ArcView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var arcColor: Int = 0xffd3cbc6U.toInt()
        set(value) {
            field = value
            invalidate()
        }

    var valColor: Int = 0xff0094c8U.toInt()
        set(value) {
            field = value
            invalidate()
        }

    var arcDeg: Float = 140F
        set(value) {
            field = value
            invalidate()
        }

    var value: Float = 67.21F
        set(value) {
            field = value
            invalidate()
        }

    var title: String = "测试测试测试"
        set(value) {
            field = value
            invalidate()
        }

    var text: String = String.format(Locale.ENGLISH, "%.2f%%", value)
        set(value) {
            field = value
            invalidate()
        }

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()

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
        // arc
        paint.strokeWidth = width.toFloat() * 0.12F
        paint.color = arcColor
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        val arcPadding = paint.strokeWidth / 2F
        rect.set(arcPadding, arcPadding, w - arcPadding, h - arcPadding)
        canvas.drawArc(rect, -90F - arcDeg, arcDeg * 2F, false, paint)
        // value
        paint.color = valColor
        canvas.drawArc(rect, -90F - arcDeg, (arcDeg * 2F) * value / 100F, false, paint)
        // text
        paint.style = Paint.Style.FILL
        paint.textSize = w * 0.1F
        paint.strokeWidth = paint.textSize * 0.15F
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(title, w / 2F, h / 2F, paint)
        canvas.drawText(text, w / 2F, h - h / 3F, paint)
    }

}