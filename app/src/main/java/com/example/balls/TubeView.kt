package com.example.balls
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TubeView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val tubeCollection = TubeCollection()

    private val paint = Paint()
    private val tubeR = mutableListOf<RectF>()
    private var tubeWidth = 0
    private var tubeHeight = 0
    private var sizeb = 50f
    private var spacing = 20f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Store the width and height in your variables
        tubeWidth = w
        tubeHeight = h
        tubeCollection.initTube(12, 5)
        // You can use tubeWidth and tubeHeight here
        // For example, you can calculate positions or sizes based on these values
    }


    // Define a callback interface for click events
    interface OnTubeClickListener {
        fun onTubeClick(rectangleIndex: Int)
    }

    var onTubeClickListener: OnTubeClickListener? = null

    override fun onDraw(canvas: Canvas) {

        val numoftube = tubeCollection.tubes.size
        val numofball = tubeCollection.tubes[0].balls.size

        super.onDraw(canvas)

        if (numoftube > 8) {
            paint.textSize = 30f
            sizeb = tubeWidth / (numoftube * 1.5f)
            spacing = (tubeWidth - (sizeb * numoftube)) / (numoftube + 1)

            val top1 = sizeb * 3
            drawtubeline(canvas, top1, numoftube, numofball)
            val top2 = tubeHeight / 2 + sizeb * 3
            drawtubeline(canvas, top2, numoftube, numofball)

        } else {

            if (numoftube > 7 && numoftube < 9) {
                paint.textSize = 30f

                sizeb = tubeWidth / (numoftube * 1.5f)

            } else if (numoftube > 5 && numoftube < 8) {
                paint.textSize = 50f

                sizeb = tubeWidth / (numoftube * 1.4f)
            } else {
                paint.textSize = 50f
                sizeb = tubeWidth / (numoftube * 1.2f)
            }
            spacing = (tubeWidth - (sizeb * numoftube)) / (numoftube + 1)

            var top = tubeHeight / 2 - sizeb
            drawtubeline(canvas, top, numoftube, numofball)

        }

    }

    fun drawtubeline(canvas: Canvas, top : Float, numoftube: Int, numofball:Int)
    {
        var left = spacing
        var right = left + sizeb
        val bottom = top + sizeb * numofball
        for (i in 0..<numoftube!!)
        {
            left = spacing + (spacing + sizeb) * i
            right = spacing + sizeb + (spacing + sizeb) * i
            drawrect(canvas, left, right, top, bottom)
            drawtube(canvas, left, right, top, bottom, i)
        }
    }     
    
    fun drawrect(canvas: Canvas, left: Float, right: Float, top: Float, bottom: Float) {
        // Set the border color and width for the tube
        paint.color = android.graphics.Color.BLACK
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        val newtop = top - 50f
        val rectF = RectF(left, newtop, right, bottom)
        tubeR.add(rectF)
        canvas.drawRoundRect(rectF, 25f, 25f, paint)
        
        paint.color = android.graphics.Color.WHITE
        paint.style = Paint.Style.FILL

        val rectFclear  = RectF(left - 10, newtop - 10, right + 10, top)
        canvas.drawRect(rectFclear, paint)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y

                // Check if the touch coordinates are within any of the rectangles
                for (i in 0 until tubeR.size) {
                    if (tubeR[i].contains(x, y)) {
                        // Notify the listener that a rectangle was clicked
                        onTubeClickListener?.onTubeClick(i)
                        return true
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    @SuppressLint("SuspiciousIndentation")
    fun drawtube(canvas: Canvas, left:Float, right: Float, top:Float, bottom: Float, index : Int) {
        
        // Set text attributes
        paint.style = Paint.Style.FILL
        paint.color = android.graphics.Color.BLACK
        // Set circle attributes
        val circleRadius =  sizeb / 2 - 5
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.color = android.graphics.Color.RED
        // Calculate the vertical spacing between texts
        val spacing = sizeb


// Access the value and isEmpty properties of each ball in the tube
        var y = bottom + sizeb/2   // Place text at the center of each segment
        val numofball = tubeCollection.tubes[index].balls.size

        for (i in 0 until numofball) {
            val ballValue = tubeCollection.tubes[index].balls[i].value
            val ballstatus  = tubeCollection.tubes[index].balls[i].isempty
         
            val text = ballValue.toString()
            val textWidth = paint.measureText(text)
             val x = (left + right - textWidth) / 2 // Center horizontally
             y = y - sizeb 
                // Draw the circle
             canvas.drawCircle(x + textWidth / 2, y, circleRadius, paint)
                // Draw the text
             canvas.drawText(text, x, y + circleRadius / 2, paint)
         }
        
    }
   
}
