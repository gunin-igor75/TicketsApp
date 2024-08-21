package com.github.gunin_igor75.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.core.common.model.ListItem

data class UiCountryItem(
    @DrawableRes val imageResId: Int,
    @StringRes val cityResId: Int
): ListItem{
    override val id: Int
        get() = hashCode()
}
