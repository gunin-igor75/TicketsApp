package com.github.gunin_igor75.presentation.screens.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseTextEditViewModel: ViewModel() {

    private val _isValidateCity: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isValidateCity: Flow<Boolean> = _isValidateCity.asStateFlow()


    fun validate(world: String) {
        _isValidateCity.value = world.isNotBlank()
    }
}