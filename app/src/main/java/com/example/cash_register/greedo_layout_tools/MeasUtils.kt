package com.example.cash_register.greedo_layout_tools

import android.content.Context
import android.util.TypedValue


object MeasUtils {
    fun pxToDp(px: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX, px.toFloat(),
            context.getResources().getDisplayMetrics()
        ).toInt()
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            context.getResources().getDisplayMetrics()
        ).toInt()
    }
}