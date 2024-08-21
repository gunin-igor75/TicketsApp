package com.core.network.dto

data class TicketsOffersContainer(
    val tickets_offers: List<TicketOfferDto>
){
    data class Price(
        val value: Int
    )
    data class TicketOfferDto(
        val id: Int,
        val title: String,
        val time_range: List<String>,
        val price: Price
    )
}
