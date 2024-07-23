package com.github.gunin_igor75.presentation.screens.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.UiEvent
import com.github.gunin_igor75.domain.usecase.GetOffers
import com.github.gunin_igor75.presentation.mappers.toUiOffers
import com.github.gunin_igor75.presentation.model.OffersStateHolder
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

class OffersViewModel(
    private val getOffers: GetOffers,
) : ViewModel() {

    private var _cityStorage: MutableStateFlow<String> = MutableStateFlow("")
    val cityStorage: StateFlow<String> = _cityStorage.asStateFlow()

    private var _offersState: MutableStateFlow<OffersStateHolder> =
        MutableStateFlow(OffersStateHolder())
    val offersState: StateFlow<OffersStateHolder> = _offersState.asStateFlow()

    private val _error: Channel<Boolean> = Channel()
    val error: Flow<Boolean> = _error.receiveAsFlow()

    private val _isValidateCity: Channel<Boolean> = Channel()

    val isValidateCity: Flow<Boolean> = _isValidateCity.receiveAsFlow()

    init {
        viewModelScope.launch {
            getUiOffersState()
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

    fun validate(world: String) {
        viewModelScope.launch {
            _isValidateCity.send( world.isNotBlank())
        }
    }
}