package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.repository.OffersRepository

class ReadCityState(
    private val repository: OffersRepository<List<Offer>>,
) {
    operator fun invoke() = repository.readCityState()
}