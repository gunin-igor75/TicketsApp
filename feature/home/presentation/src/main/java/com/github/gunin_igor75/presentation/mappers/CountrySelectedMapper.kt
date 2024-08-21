package com.github.gunin_igor75.presentation.mappers

import com.core.common.R
import com.github.gunin_igor75.domain.model.TicketsOffers
import com.github.gunin_igor75.presentation.model.UiTicketOffer


fun List<TicketsOffers>.toUiTicketsOffers() = mapIndexed { index, item ->
    UiTicketOffer(
        id = item.id,
        title = item.title,
        timeRange = item.timeRange,
        price = item.price,
        imageResId = getDrawableResId(index)
    )
}

fun getDrawableResId(index: Int) =
    when (index) {
        0 -> R.drawable.circle_red
        1 -> R.drawable.circle_blue
        2 -> R.drawable.circle_white
        else -> throw IllegalStateException("Size array tickets_offers more 3")
    }
