package com.github.gunin_igor75.domain.model

sealed interface StateScreen<T> {
    class Initial<T> : StateScreen<T>
    class Loading<T> : StateScreen<T>
    data class Success<T>(
        val value: T,
    ) : StateScreen<T>
}