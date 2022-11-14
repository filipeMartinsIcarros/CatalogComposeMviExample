package com.example.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class MviViewModel<S: UiState>(initialVal: S): ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)

    val state: StateFlow<S>
        get() = _state

    protected suspend fun emit(value: S) {
        _state.tryEmit(value)
    }
}

interface UiState