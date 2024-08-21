package com.core.common.extension

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

fun Fragment.dpToPx(dpValue: Int): Int {
    val dpRatio = resources.displayMetrics.density
    return (dpValue * dpRatio).toInt()
}

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Bundle.getParcelableProvider(identifierParameter: String): T? {

    return if (Build.VERSION.SDK_INT >= 33) {
        this.getParcelable(identifierParameter, T::class.java)
    } else {
        this.getParcelable(identifierParameter)
    }

}

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Bundle.getParcelableArrayListProvider(identifierParameter: String): java.util.ArrayList<T>? {

    return if (Build.VERSION.SDK_INT >= 33) {
        this.getParcelableArrayList(identifierParameter, T::class.java)
    } else {
        this.getParcelableArrayList(identifierParameter)
    }
}