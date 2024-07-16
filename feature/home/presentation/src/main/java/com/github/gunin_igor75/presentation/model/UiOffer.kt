package com.github.gunin_igor75.presentation.model

import com.core.common.model.ListItem

data class UiOffer(
    override val id: Int,
    val title: String = "",
    val town: String = "",
    val price: String = "",
    val imageResId: Int = 0
): ListItem
