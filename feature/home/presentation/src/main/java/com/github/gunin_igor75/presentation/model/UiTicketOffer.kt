package com.github.gunin_igor75.presentation.model

import androidx.annotation.DrawableRes
import com.core.common.model.ListItem

data class UiTicketOffer(
    override val id: Int,
    val title: String,
    val timeRange: String,
    val price: String,
    @DrawableRes val imageResId: Int
) : ListItem
