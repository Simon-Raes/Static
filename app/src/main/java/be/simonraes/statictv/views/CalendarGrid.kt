package be.simonraes.statictv.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import be.simonraes.statictv.R

/**
 * Created by SimonRaes on 09/04/16.
 */
class CalendarGrid : View {

    val paintTest: Paint
    val paintText: Paint


    var viewWidth = 0F
    var viewHeight = 0F
    var spacingHorizontal = 0F

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        paintTest = Paint()
        paintTest.color = ContextCompat.getColor(context, R.color.colorAccent)
        paintText = Paint()
        paintText.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        paintText.textSize = 50.toFloat()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        viewWidth = width.toFloat()
        viewHeight = height.toFloat()
        spacingHorizontal = viewWidth / 8

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)



        canvas.drawRect(0F, 0F, viewWidth, viewHeight, paintTest)

        val offSet = paintText.measureText("1")

        // todo loops and stuff

        canvas.drawText("1", spacingHorizontal * 1 - offSet / 2, viewHeight / 4, paintText)
        canvas.drawText("2", spacingHorizontal * 2 - offSet / 2, viewHeight / 4, paintText)
        canvas.drawText("3", spacingHorizontal * 3 - offSet / 2, viewHeight / 4, paintText)
        canvas.drawText("4", spacingHorizontal * 4 - offSet / 2, viewHeight / 4, paintText)
        canvas.drawText("5", spacingHorizontal * 5 - offSet / 2, viewHeight / 4, paintText)
        canvas.drawText("6", spacingHorizontal * 6 - offSet / 2, viewHeight / 4, paintText)
        canvas.drawText("7", spacingHorizontal * 7 - offSet / 2, viewHeight / 4, paintText)
    }

}