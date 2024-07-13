package com.github.gunin_igor75.domain.usecase

import com.core.common.model.DataResult
import com.core.common.model.UiEvent
import com.github.gunin_igor75.domain.model.Ticket
import com.github.gunin_igor75.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.flow

class GetTickets(
    private val repository: TicketsRepository<List<Ticket>>,
) {
    suspend operator fun invoke() = flow {
        emit(UiEvent.Loading())
        when (val response = repository.getTickets()) {
            is DataResult.Success -> {
                emit(UiEvent.Success(data = response.data))
            }

            is DataResult.Error -> {
                emit(UiEvent.Error(message = response.message))
            }
        }
    }
}