package com.github.gunin_igor75.presentation.screens.destination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.utils.Constants.Companion.EMPTY_LINE
import com.core.common.utils.Constants.Companion.TICKET_ID
import com.github.gunin_igor75.domain.model.TicketModel
import com.github.gunin_igor75.domain.usecase.GetCountryItems
import com.github.gunin_igor75.domain.usecase.GetTicket
import com.github.gunin_igor75.domain.usecase.SaveTicket
import com.github.gunin_igor75.presentation.mappers.toCountryItems
import com.github.gunin_igor75.presentation.screens.base.BaseTextEditViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FindCountryViewModel(
    private val getCountryItems: GetCountryItems,
    private val saveTicket: SaveTicket,
    getTicket: GetTicket,
) : BaseTextEditViewModel() {

    val cityFromState: Flow<TicketModel> = getTicket(TICKET_ID)

    fun countryItems() = getCountryItems()
        .map { it.toCountryItems() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )


    fun saveCity(ticketModel: TicketModel) {
        viewModelScope.launch {
            saveTicket(ticketModel)
        }
    }
}