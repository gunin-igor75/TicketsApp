package com.github.gunin_igor75.presentation.screens.destination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.domain.usecase.GetCountryItems
import com.github.gunin_igor75.presentation.mappers.toCountryItems
import com.github.gunin_igor75.presentation.screens.base.BaseTextEditViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FindCountryViewModel(
    private val getCountryItems: GetCountryItems,
) : ViewModel() {

    fun countryItems() = getCountryItems()
        .map { it.toCountryItems() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )


    fun validateCity(city: String): Boolean {
        return city.isNotBlank()
    }
}