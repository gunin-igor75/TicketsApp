package com.github.gunin_igor75.presentation.model

import com.core.common.model.ListItem

data class UiTicket(
    override val id: Int,
    val price: String,
    val badge: String?,
    val timeDeparture: String,
    val timeArrival: String,
    val timeFlight: String,
    val transfer:Boolean,
    val airportArrival: String,
    val airportDeparture: String
): ListItem
