package com.github.gunin_igor75.domain.usecase

import com.core.common.model.DataResult
import com.core.common.model.UiEvent
import com.github.gunin_igor75.domain.model.TicketsOffers
import com.github.gunin_igor75.domain.repository.TicketSOffersRepository
import kotlinx.coroutines.flow.flow

class GetTicketsOffers(
    private val repository: TicketSOffersRepository<TicketsOffers>,
) {
    suspend operator fun invoke() = flow {
        emit(UiEvent.Loading())
        when (val response = repository.getRecommendationTickets()) {
            is DataResult.Success -> {
                emit(UiEvent.Success(data = response.data))
            }

            is DataResult.Error -> {
                emit(UiEvent.Error(message = response.message))
            }
        }
    }
}

