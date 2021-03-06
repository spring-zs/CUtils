package com.carlos.cutils.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.carlos.cutils.R
import com.carlos.cutils.util.LogUtils

/**
 * Github: https://github.com/xbdcc/.
 * Created by Carlos on 2020/2/26.
 */
open class RoundRectButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    var paint: Paint = Paint()
    var rectF = RectF(0f, 0f, 0f, 0f)
    var path = Path()
    var color = Color.BLUE
    var radius = 0f
    var topLeftRadius = 20f
    var topRightRadius = 0f
    var bottomLeftRadius = 20f
    var bottomRightRadius = 0f

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.RoundRectButton, defStyleAttr, 0)
        color = typedArray.getColor(R.styleable.RoundRectButton_solidColor, Color.BLUE)
        radius = typedArray.getDimension(R.styleable.RoundRectButton_radius, 0f)
        topLeftRadius = typedArray.getDimension(R.styleable.RoundRectButton_topLeftRadius, 0f)
        topRightRadius = typedArray.getDimension(R.styleable.RoundRectButton_topRightRadius, 0f)
        bottomLeftRadius = typedArray.getDimension(R.styleable.RoundRectButton_bottomLeftRadius, 0f)
        bottomRightRadius =
            typedArray.getDimension(R.styleable.RoundRectButton_bottomRightRadius, 0f)
        LogUtils.isShowLog = true
//        LogUtils.d("typedArray"+ typedArray)
//        LogUtils.d("radius"+ radius)
//        LogUtils.d("topLeftRadius"+ topLeftRadius)
//        LogUtils.d("topRightRadius"+ topRightRadius)
//        LogUtils.d("bottomLeftRadius"+ bottomLeftRadius)
        typedArray.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        paint.isAntiAlias = true
        paint.color = color
        paint.style = Paint.Style.FILL

        if (radius != 0f)
            drawTotalRound(canvas)
        else
            drawPartRound(canvas)
        super.onDraw(canvas)
    }

    private fun drawTotalRound(canvas: Canvas) {
        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, paint)
    }

    private fun drawPartRound(canvas: Canvas) {
        path.addRoundRect(
            rectF,
            floatArrayOf(
                topLeftRadius,
                topLeftRadius,
                topRightRadius,
                topRightRadius,
                bottomRightRadius,
                bottomRightRadius,
                bottomLeftRadius,
                bottomLeftRadius
            ),
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)
    }

}

