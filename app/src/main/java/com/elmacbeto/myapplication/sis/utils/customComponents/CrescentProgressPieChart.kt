package com.elmacbeto.myapplication.sis.utils.customComponents

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.elmacbeto.myapplication.R

class CrescentProgressPieChart(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var progress: Float = 0f
    private var strokeWidth: Float = 10f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val flipMatrix = Matrix()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        flipMatrix.setScale(1f, -1f, width / 2f, height / 2f) // Flip matrix along y-axis
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val centerX = width / 2
        val centerY = height / 2
        val radius = Math.min(width, height) / 2 - strokeWidth / 2

        // Draw the outer circle
        paint.color = context.getColor(R.color.white)
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw the half circle representing progress
        paint.color = context.getColor(R.color.blue)
        val startAngle = 0f // Start angle for the half circle
        val sweepAngle = 180f * progress// Sweep angle covering half of the circle
        val path = Path()
        path.arcTo(centerX - radius, centerY - radius, centerX + radius, centerY + radius, startAngle, sweepAngle, false)

        canvas.scale(-1f, -1f, width / 2f, height / 2f)
        canvas.drawPath(path, paint)
    }

    fun setProgress(progress: Float) {
        val newProgress = if (progress > 100 || progress < 0)100f else progress
        this.progress = newProgress/100
        invalidate()
    }
}