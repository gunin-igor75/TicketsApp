package com.github.gunin_igor75.presentation.screens.offers

import androidx.lifecycle.viewModelScope
import com.core.common.model.UiEvent
import com.github.gunin_igor75.domain.usecase.GetOffers
import com.github.gunin_igor75.domain.usecase.ReadCityState
import com.github.gunin_igor75.domain.usecase.SaveCityState
import com.github.gunin_igor75.presentation.mappers.toUiOffers
import com.github.gunin_igor75.presentation.model.OffersStateHolder
import com.github.gunin_igor75.presentation.screens.base.BaseTextEditViewModel
import com.github.gunin_igor75.presentation.utils.listLoading
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OffersTextEditViewModel(
    private val getOffers: GetOffers,
    private val saveCityState: SaveCityState,
    readCityState: ReadCityState,
) : BaseTextEditViewModel() {

    val cityStorage: Flow<String> = readCityState()

    private var _offersState: MutableStateFlow<OffersStateHolder> =
        MutableStateFlow(OffersStateHolder())
    val offersState: StateFlow<OffersStateHolder> = _offersState.asStateFlow()

    private val _error: Channel<Boolean> = Channel()
    val error: Flow<Boolean> = _error.receiveAsFlow()


    init {
        getUiOffersState()
    }


    fun saveCity(city: String) {
        viewModelScope.launch {
            saveCityState(city)
        }
    }

    private fun getUiOffersState() {
        viewModelScope.launch {
            getOffers().onEach { event ->
                when (event) {
                    is UiEvent.Error -> {
                        _error.send(true)
                    }

                    is UiEvent.Loading -> {
                        _offersState.value = OffersStateHolder(data = listLoading)
                    }

                    is UiEvent.Success -> {
                        _offersState.value = OffersStateHolder(data = event.data?.toUiOffers())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}