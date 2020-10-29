package com.example.mywebview.util


import android.content.Context

object DensityUtil {

    private var density = -1f
    private var widthPixels = -1
    private var heightPixels = -1

    fun getDensity(context: Context): Float {
        if (density <= 0f) {
            density = context.resources.displayMetrics.density
        }
        return density
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        return (dpValue * getDensity(context) + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        return (pxValue / getDensity(context) + 0.5f).toInt()
    }

    fun getScreenWidth(context: Context): Int {
        if (widthPixels <= 0) {
            widthPixels = context.resources.displayMetrics.widthPixels
        }
        return widthPixels
    }


    fun getScreenHeight(context: Context): Int {
        if (heightPixels <= 0) {
            heightPixels = context.resources.displayMetrics.heightPixels
        }
        return heightPixels
    }
}