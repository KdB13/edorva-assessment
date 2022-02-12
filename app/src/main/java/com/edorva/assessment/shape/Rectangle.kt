package com.edorva.assessment.shape

import android.graphics.Canvas
import android.graphics.Paint

class Rectangle(private val paint: Paint) : BoundedShape() {

    override fun draw(canvas: Canvas) {
        drawRectangle(canvas)
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

    private fun drawRectangle(canvas: Canvas) {
        // Draw the rectangle
        canvas.drawRect(getRectBounds(), paint)
    }
}