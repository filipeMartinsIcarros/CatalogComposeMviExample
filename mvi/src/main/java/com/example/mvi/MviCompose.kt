package com.example.mvi


interface MviCompose{
    abstract fun render(state: UiState)
}

interface Interface<S: Any> {
    abstract fun render(state: S)
}

abstract class AbstractTest<S: Any>{
    abstract fun render(state: S)
}