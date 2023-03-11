package com.giussepr.ceiba.ui.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.domain.model.fold
import com.giussepr.ceiba.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    private fun onLoadUsers() {
        getUsersUseCase().map { result ->
            result.fold(
                onSuccess = { users ->
                    _uiState.value = HomeUiState.Success(users)
                },
                onFailure = {
                    _uiState.value = HomeUiState.Error(it.message)
                })
        }.onStart { _uiState.value = HomeUiState.Loading }
            .flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun onUiEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            is HomeUiEvent.LoadUsers -> {
                onLoadUsers()
            }
        }
    }

    sealed class HomeUiState {
        object Loading : HomeUiState()
        data class Success(val users: List<User>) : HomeUiState()
        data class Error(val message: String) : HomeUiState()
    }

    sealed class HomeUiEvent {
        object LoadUsers : HomeUiEvent()
    }

}