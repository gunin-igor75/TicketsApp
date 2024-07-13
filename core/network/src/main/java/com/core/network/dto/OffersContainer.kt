package com.core.network.dto

data class OffersContainer(
    val offers: List<OfferDto>
){
    data class Price(
        val value: Int
    )
    data class OfferDto(
        val id: Int,
        val title: String,
        val town: String,
        val price: Price
    )
}
