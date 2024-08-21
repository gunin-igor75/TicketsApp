package com.github.gunin_igor75.domain.model

data class Ticket(
    val id: Int,
    val price: String,
    val badge: String?,
    val timeDeparture: String,
    val timeArrival: String,
    val timeFlight: String,
    val transfer:Boolean,
    val airportArrival: String,
    val airportDeparture: String
)
