package com.edorva.assessment.shape

import android.graphics.Canvas

/**
 * The shape to draw like line, rectangle, arrow, or oval.
 */
abstract class Shape : TouchEventListener {
    var isDrawing = false

    abstract fun draw(canvas: Canvas)

    enum class Type {
        LINE, ARROW, RECTANGLE, ELLIPSE
    }
}

interface TouchEventListener {
    fun onDown(x: Float, y: Float)
    fun onMove(x: Float, y: Float)
    fun onUp(x: Float, y: Float)
}