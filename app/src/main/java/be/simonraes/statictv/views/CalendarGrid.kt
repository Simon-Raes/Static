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
    var spacingVertical = 0F
    val days = 31 // make this dynamic, can be 28 - 31
    val columns = 7
    val rows: Int

    // Selected area
    var midSectionStart = -1
    var midSectionSEnd = -1
    var midSectionForRow = -1


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        paintTest = Paint()
        paintTest.color = ContextCompat.getColor(context, R.color.colorAccent)
        paintText = Paint()
        paintText.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        paintText.textSize = 50.toFloat()

        val extraRow = if ((days % 7) > 0) 1 else 0

        rows = days / 7 + extraRow

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        viewWidth = width.toFloat()
        viewHeight = height.toFloat()
        spacingHorizontal = viewWidth / 8
        spacingVertical = viewHeight / 6
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0F, 0F, viewWidth, viewHeight, paintTest)

        val offSet = paintText.measureText("1")

        var r = 1
        var c = 1
        for (i in 1..days) {
            println(r)
            println(c)

            canvas.drawText(i.toString(), spacingHorizontal * c - offSet / 2, spacingVertical * r, paintText)

            c++
            if(c > columns){
                // start from the left again on the next row
                c = 1
                r++
            }

        }

    }

}