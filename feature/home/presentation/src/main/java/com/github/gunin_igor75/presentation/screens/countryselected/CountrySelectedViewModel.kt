package com.github.gunin_igor75.presentation.screens.countryselected

import androidx.lifecycle.viewModelScope
import com.core.common.model.UiEvent
import com.core.common.utils.Constants.Companion.TICKET_ID
import com.github.gunin_igor75.domain.model.TitleTicketModel
import com.github.gunin_igor75.domain.usecase.GetTicket
import com.github.gunin_igor75.domain.usecase.GetTicketsOffers
import com.github.gunin_igor75.domain.usecase.SaveTicket
import com.github.gunin_igor75.presentation.mappers.toUiTicketsOffers
import com.github.gunin_igor75.presentation.model.HomeStateHolder
import com.github.gunin_igor75.presentation.screens.base.BaseTextEditViewModel
import com.github.gunin_igor75.presentation.utils.buttonItemList
import com.github.gunin_igor75.presentation.utils.listLoading
import com.github.gunin_igor75.presentation.utils.titleItemList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CountrySelectedViewModel(
    private val getTicketsOffers: GetTicketsOffers,
    private val saveTicket: SaveTicket,
    getTicket: GetTicket,
): BaseTextEditViewModel() {

    val cityFromState: Flow<TitleTicketModel> = getTicket(TICKET_ID)

    private val _error: Channel<Boolean> = Channel()
    val error: Flow<Boolean> = _error.receiveAsFlow()

    private var _ticketsOffers: MutableStateFlow<HomeStateHolder> =
        MutableStateFlow(HomeStateHolder())
    val ticketsOffers: StateFlow<HomeStateHolder> = _ticketsOffers.asStateFlow()


    init {
        getTicketsOffersState()
    }

    private fun getTicketsOffersState() {
        viewModelScope.launch {
            getTicketsOffers().onEach { event ->
                when (event) {
                    is UiEvent.Error -> {
                        _error.send(true)
                    }

                    is UiEvent.Loading -> {

                        _ticketsOffers.value = HomeStateHolder(data = titleItemList + listLoading + buttonItemList)
                    }

                    is UiEvent.Success -> {
                        val list = event.data?.toUiTicketsOffers()
                            ?: throw IllegalStateException("Success data is null")
                        _ticketsOffers.value =
                            HomeStateHolder(data = titleItemList + list + buttonItemList)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun saveCity(titleTicketModel: TitleTicketModel) {
        viewModelScope.launch {
            saveTicket(titleTicketModel)
        }
    }
}