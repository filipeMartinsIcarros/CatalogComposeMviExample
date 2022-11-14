package com.example.composemvicatalogo.ui.home.lifestyles.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.composemvicatalogo.core.models.SearchLifeStyleItemModel
import com.example.composemvicatalogo.core.models.SearchLifeStyleResponse
import com.example.composemvicatalogo.core.retrofit.RetrofitRequest
import com.example.composemvicatalogo.core.services.Services
import com.example.composemvicatalogo.ui.navigation.Arguments.SearchLifeStyleArgument
import com.example.mvi.MviViewModel
import com.example.mvi.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchLifeStyleViewModel (
    savedStateHandle: SavedStateHandle,
    private val service: Services
) : MviViewModel<SearchLifeStyleState>(SearchLifeStyleState.initial()) {

    private val sop: String = checkNotNull(savedStateHandle[SearchLifeStyleArgument])

    fun getSearchLifeStyles() = viewModelScope.launch(Dispatchers.Default) {
        emit(SearchLifeStyleState.Loading)
        val response = RetrofitRequest.doRequest(call = { service.getSearchLifeStyle(sop) })
        emit(reduce(response))
    }

    private fun reduce(response: RetrofitRequest.RetrofitTreatedRequest<SearchLifeStyleResponse>) = when {
        response.hasError -> SearchLifeStyleState.Error(response.message ?: "Erro inesperado")
        else -> {
            if(response.response != null) handleSuccess(response.response.list)
            else SearchLifeStyleState.Error("Null error")
        }
    }

    private fun handleSuccess(response: List<SearchLifeStyleItemModel>): SearchLifeStyleState {
        return SearchLifeStyleState.Success(response)
    }
}

sealed class SearchLifeStyleState : UiState {
    class Error(val message: String): SearchLifeStyleState()
    object Loading : SearchLifeStyleState()
    object Initial : SearchLifeStyleState()
    class Success(val list: List<SearchLifeStyleItemModel>): SearchLifeStyleState()

    companion object {
        fun initial() = Initial
    }
}