package com.edorva.assessment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.edorva.assessment.shape.*
import java.lang.IllegalArgumentException

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        const val STROKE_WIDTH = 15.0f
    }

    private lateinit var bitmap: Bitmap
    private lateinit var currentShape: Shape
    private lateinit var finalCanvas: Canvas
    private var paint: Paint
    private var color: Int

    init {
        // Set default color of BLACK
        color = ContextCompat.getColor(context, R.color.palette_black)

        paint = Paint().apply {
            strokeWidth = STROKE_WIDTH
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            color = this@DrawingView.color
            isDither = true
        }
    }

    fun setShape(type: Shape.Type) {
        currentShape = when (type) {
            Shape.Type.LINE -> Line(paint)
            Shape.Type.RECTANGLE -> Rectangle(paint)
            Shape.Type.ELLIPSE -> Ellipse(paint)
            Shape.Type.ARROW -> Arrow(paint)
            else -> throw IllegalArgumentException("Unknown shape type: $type")
        }
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        finalCanvas = Canvas(bitmap)

        // Set default shape to LINE
        setShape(Shape.Type.LINE)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Draw the current canvas everytime
        canvas?.drawBitmap(bitmap, 0.0f, 0.0f, paint)

        if (currentShape.isDrawing) {
            currentShape.draw(canvas!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val mx = event!!.x
        val my = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> currentShape.onDown(mx, my)
            MotionEvent.ACTION_MOVE -> currentShape.onMove(mx, my)
            MotionEvent.ACTION_UP -> {
                currentShape.apply {
                    onUp(mx, my)

                    // Save to the final canvas
                    draw(finalCanvas)
                }
            }
        }

        invalidate()

        return true
    }
}