package com.core.common.utils

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun string(@StringRes id: Int): String
    fun drawable(@DrawableRes id: Int):Drawable
}