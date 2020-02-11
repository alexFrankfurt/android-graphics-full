package com.alex.graphics

import android.content.Context
import android.graphics.*
import android.view.View


class CanvasView(ctx: Context, val paint: Paint) : View(ctx) {


    val rectF = RectF(100f, 400f, 300f, 600f)
    val arr: Array<Float> = arrayOf(70f, 420f, 240f, 300f, 240f, 300f, 410f, 420f)
    val arc = RectF(400f, 400f, 750f, 600f)
    val path: Path = Path()

    init {
        with(path) {
            reset()
            moveTo(40f, 420f)
            lineTo(240f, 300f)
            lineTo(380f, 420f)
        }

        with(paint) {
            color = Color.RED
            strokeWidth = 10f
            style = Paint.Style.FILL
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(path, paint)

        with(canvas) {
            drawRGB(210, 255, 210)
            drawRoundRect(rectF, 10f, 10f, paint)
            drawPath(path, paint)
        }

        path.reset()

        with(path) {
            moveTo(94f, 56f)
            quadTo(100f, 39f, 119f, 32f)

            moveTo(119f, 32f)
            quadTo(112f, 49f, 94f, 56f)

//            moveTo(160f, 150f)
//            quadTo(300f, 200f, 310f, 150f)
//            quadTo(450f, 80f, 520f, 150f)
        }

        with(canvas) {
            drawTextOnPath("Hello world from view lorem ipsum ipsum ipsum image text something", path, 0f, 0f, paint)
            drawCircle(540f, 320f, 50f, paint)
            drawArc(arc, 0f, 120f, true, paint)
        }

    }

    private fun getPointOnQuad(p1: PointF, p2: PointF, p3: PointF, p: Float): PointF {
        val x1 = (p2.x - p1.x) * p + p1.x
        val y1 = (p2.y - p1.y) * p + p1.y
        val x2 = (p3.x - p2.x) * p + p2.x
        val y2 = (p3.y - p2.y) * p + p2.y
        return PointF((x2 - x1) * p + x1, (y2 - y1) * p + y1)
    }
}
