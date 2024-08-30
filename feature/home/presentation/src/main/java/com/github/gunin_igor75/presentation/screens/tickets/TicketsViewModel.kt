package com.github.gunin_igor75.presentation.screens.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.UiEvent
import com.core.common.utils.Constants.Companion.TICKET_ID
import com.github.gunin_igor75.domain.usecase.GetTicket
import com.github.gunin_igor75.domain.usecase.GetTickets
import com.github.gunin_igor75.presentation.mappers.toUiTickets
import com.github.gunin_igor75.presentation.mappers.toUiTitleTicket
import com.github.gunin_igor75.presentation.model.HomeStateHolder
import com.github.gunin_igor75.presentation.model.UiTitleTicket
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TicketsViewModel(
    private val getTickets: GetTickets,
    getTicket: GetTicket,
) : ViewModel() {

    val titleTicketState: Flow<UiTitleTicket> = getTicket(TICKET_ID).map { it.toUiTitleTicket() }

    private val _error: Channel<Boolean> = Channel()
    val error: Flow<Boolean> = _error.receiveAsFlow()

    private val _ticketsState = MutableStateFlow(HomeStateHolder())
    val ticketsState: StateFlow<HomeStateHolder> = _ticketsState.asStateFlow()

    init {
        getTicketsState()
    }


    private fun getTicketsState() {
        viewModelScope.launch {
            getTickets().onEach { event ->
                when (event) {
                    is UiEvent.Error -> {
                        _error.send(true)
                    }

                    is UiEvent.Loading -> {
                        _ticketsState.value = HomeStateHolder(loading = true)
                    }

                    is UiEvent.Success -> {
                        _ticketsState.value = HomeStateHolder(data = event.data?.toUiTickets())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}