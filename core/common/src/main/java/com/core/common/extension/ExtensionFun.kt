package com.core.common.extension

import androidx.fragment.app.Fragment

fun Fragment.dpToPx(dpValue: Int): Int {
    val dpRatio = resources.displayMetrics.density
    return (dpValue * dpRatio).toInt()
}
