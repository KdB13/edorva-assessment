package com.edorva.assessment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.edorva.assessment.databinding.ActivityMainBinding
import com.edorva.assessment.shape.Shape

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentShapeRadio = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentShapeRadio = binding.options.checkedRadioButtonId

        binding.activity = this

        binding.options.setOnCheckedChangeListener { _, checkedId ->
            // Hide the color palette, if other option is picked
            if (checkedId != R.id.radio_pallete) {
                showPalette(false)
                currentShapeRadio = checkedId
            }

            when (checkedId) {
                R.id.radio_pencil -> changeShape(Shape.Type.LINE)
                R.id.radio_arrow -> changeShape(Shape.Type.ARROW)
                R.id.radio_square -> changeShape(Shape.Type.RECTANGLE)
                R.id.radio_circle -> changeShape(Shape.Type.ELLIPSE)
            }
        }
    }

    private fun changeShape(type: Shape.Type) {
        binding.drawingView.setShape(type)
    }

    fun changeColor(color: Int) {
        // Change the current drawing color
        binding.drawingView.setColor(color)

        // Check the current shape's radio button
        binding.options.check(currentShapeRadio)

        // Hide the color palette
        showPalette(false)
    }

    fun showPalette(isVisible: Boolean) {
        if (isVisible) binding.colorPalette.visibility = View.VISIBLE
        else binding.colorPalette.visibility = View.GONE
    }

}