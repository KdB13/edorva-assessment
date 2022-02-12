package com.edorva.assessment.shape

import android.graphics.RectF

abstract class BoundedShape : Shape() {

    // Shape bounds
    protected var startX: Float = .0f
    protected var startY: Float = .0f

    protected var endX: Float = .0f
    protected var endY: Float = .0f

    protected fun getRectBounds(): RectF {
        return RectF().apply {
            right = if (startX > endX) startX else endX
            left = if (startX > endX) endX else startX
            bottom = if (startY > endY) startY else endY
            top = if (startY > endY) endY else startY
        }
    }
}