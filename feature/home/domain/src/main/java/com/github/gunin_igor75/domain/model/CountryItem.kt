package com.github.gunin_igor75.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CountryItem(
    @DrawableRes val imageResId: Int,
    @StringRes val cityResId: Int
)
