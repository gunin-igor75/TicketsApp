package com.github.gunin_igor75.domain.model

sealed interface StateError {
    data object ErrorInternet : StateError
    data class Error(
        val message: String,
    ) : StateError
}