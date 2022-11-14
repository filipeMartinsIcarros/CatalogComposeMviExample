package com.example.composemvicatalogo.ui.home.lifestyles

import androidx.lifecycle.viewModelScope
import com.example.composemvicatalogo.core.models.LifeStyleListItemModel
import com.example.composemvicatalogo.core.models.LifeStyleResponse
import com.example.composemvicatalogo.core.retrofit.RetrofitRequest
import com.example.composemvicatalogo.core.services.Services
import com.example.mvi.MviViewModel
import com.example.mvi.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LifeStyleViewModel (
    private val service: Services
) : MviViewModel<LifeStyleState>(LifeStyleState.initial()) {

    var listLifeStyle: List<LifeStyleListItemModel> = emptyList()

    fun getLifeStyles() = viewModelScope.launch(Dispatchers.Default) {
        emit(LifeStyleState.Loading)
        val response = RetrofitRequest.doRequest(call = { service.getLifeStyles() })
        emit(reduce(response))
    }

    private fun reduce(response: RetrofitRequest.RetrofitTreatedRequest<LifeStyleResponse>) = when {
        response.hasError -> LifeStyleState.Error(response.message ?: "Erro inesperado")
        else -> {
            if(response.response != null) {
                listLifeStyle = response.response.list
                handleSuccess(response.response.list)
            }
            else LifeStyleState.Error("Null error")
        }
    }

    private fun handleSuccess(response: List<LifeStyleListItemModel>): LifeStyleState {
        return LifeStyleState.Success(response)
    }
}

sealed class LifeStyleState : UiState {
    class Error(val message: String): LifeStyleState()
    object Loading : LifeStyleState()
    object Initial : LifeStyleState()
    class Success(val list: List<LifeStyleListItemModel>): LifeStyleState()

    companion object {
        fun initial() = Initial
    }
}

