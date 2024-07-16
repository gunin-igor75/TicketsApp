package com.github.gunin_igor75.presentation.mappers

import com.core.common.R
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.presentation.model.UiOffer


fun List<Offer>.toUiOffers() = map { it.toUiOffer() }

fun Offer.toUiOffer() = UiOffer(
    id = id,
    title = title,
    town = town,
    price = price,
    imageResId = getImageResIdById(id)
)

private fun getImageResIdById(id: Int): Int =
    when (id) {
        1 -> {
            R.drawable.im_die
        }
        2 -> {
            R.drawable.im_socrat
        }
        else ->{
            R.drawable.im_lampabikt
        }
    }