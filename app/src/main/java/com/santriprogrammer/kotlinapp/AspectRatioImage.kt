package com.santriprogrammer.kotlinapp

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by fikriimaduddin on 11/3/17.
 */
class AspectRatioImage @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : ImageView(context, attrs, defStyle) {

    var measureOnceListener: OnMeasureListener? = null
    private var widthRatio = 1f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = Math.round(width * widthRatio)
        setMeasuredDimension(width, height)

        measureOnceListener?.onViewMeasure(width, height)
        measureOnceListener = null
    }

    interface OnMeasureListener {
        fun onViewMeasure(width: Int, height: Int)
    }
}
