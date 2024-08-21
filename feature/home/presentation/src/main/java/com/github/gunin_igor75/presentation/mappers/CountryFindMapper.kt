package com.github.gunin_igor75.presentation.mappers

import com.github.gunin_igor75.domain.model.CountryItem
import com.github.gunin_igor75.presentation.model.UiCountryItem


fun List<CountryItem>.toCountryItems() = map { it.toUiCountryItem() }

fun CountryItem.toUiCountryItem() = UiCountryItem(
    imageResId = imageResId,
    cityResId = cityResId
)

