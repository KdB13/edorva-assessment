package com.edorva.assessment.shape

import android.graphics.Canvas
import android.graphics.Paint

class Ellipse(private val paint: Paint) : BoundedShape() {

    override fun draw(canvas: Canvas) {
        drawEllipse(canvas)
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
        endX = x
        endY = y
    }

    private fun drawEllipse(canvas: Canvas) {
        // Draw the rectangle
        canvas.drawOval(getRectBounds(), paint)
    }
}