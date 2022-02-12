package com.edorva.assessment.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Line(private val paint: Paint) : Shape() {

    private val currentPath: Path = Path()

    override fun draw(canvas: Canvas) {
        canvas.drawPath(currentPath, paint)
    }

    override fun onDown(x: Float, y: Float) {
        isDrawing = true
        currentPath.reset()
        currentPath.moveTo(x, y)
    }

    override fun onMove(x: Float, y: Float) {
        currentPath.lineTo(x, y)
    }

    override fun onUp(x: Float, y: Float) {
        isDrawing = false
    }
}