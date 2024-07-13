package com.github.gunin_igor75.domain.usecase

import com.core.common.model.DataResult
import com.core.common.model.UiEvent
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.repository.OffersRepository
import kotlinx.coroutines.flow.flow

class GetOffers(
    private val repository: OffersRepository<List<Offer>>,
) {
    suspend operator fun invoke() = flow {
        emit(UiEvent.Loading())
        when (val response = repository.getOffers()) {
            is DataResult.Success -> {
                emit(UiEvent.Success(data = response.data))
            }

            is DataResult.Error -> {
                emit(UiEvent.Error(message = response.message))
            }
        }
    }
}