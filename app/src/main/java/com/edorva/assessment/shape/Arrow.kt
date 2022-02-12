package com.edorva.assessment.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.sqrt

class Arrow(private val paint: Paint) : BoundedShape() {

    companion object {
        const val ARROW_LENGTH = 8
        const val ARROW_GAP = 20
    }

    override fun draw(canvas: Canvas) {
        // Draw the line
        canvas.drawLine(startX, startY, endX, endY, paint)

        // Draw the arrow
        drawArrow(canvas)
    }

    override fun onDown(x: Float, y: Float) {
        isDrawing = true
        startX = x
        startY = y
        endX = x
        endY = y
    }

    override fun onMove(x: Float, y: Float) {
        endX = x
        endY = y
    }

    override fun onUp(x: Float, y: Float) {
        isDrawing = false
    }

    private fun drawArrow(canvas: Canvas) {
        val deltaX: Float = endX - startX
        val deltaY: Float = endY - startY
        val distance = sqrt((deltaX * deltaX + deltaY * deltaY).toDouble())
        val frac = (1 / (distance / 35)).toFloat()

        val pointX1: Float = startX + ((1 - frac) * deltaX + frac * deltaY)
        val pointY1: Float = startY + ((1 - frac) * deltaY - frac * deltaX)

        val pointX2: Float = startX + ((1 - frac) * deltaX - frac * deltaY)
        val pointY2: Float = startY + ((1 - frac) * deltaY + frac * deltaX)

        val path = Path().apply {
            fillType = Path.FillType.EVEN_ODD
            moveTo(pointX1, pointY1)
            lineTo(endX, endY)
            moveTo(pointX2, pointY2)
            lineTo(endX, endY)
            close()
        }

        canvas.drawPath(path, paint)
    }
}