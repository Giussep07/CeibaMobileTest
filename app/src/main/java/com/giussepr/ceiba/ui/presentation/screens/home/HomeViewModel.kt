package com.giussepr.ceiba.ui.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.domain.model.fold
import com.giussepr.ceiba.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private var usersList: List<User> = emptyList()

    private fun onLoadUsers() {
        getUsersUseCase().map { result ->
            result.fold(
                onSuccess = { users ->
                    this.usersList = users
                    uiState = uiState.copy(users = users, isLoading = false)
                },
                onFailure = {
                    uiState = uiState.copy(errorMessage = it.message, isLoading = false)
                })
        }.onStart { uiState = uiState.copy(isLoading = true) }
            .flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun onUiEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            is HomeUiEvent.LoadUsers -> {
                onLoadUsers()
            }
        }
    }

    fun onSearchTermChanged(searchTerm: String) {
        uiState = uiState.copy(searchTerm = mutableStateOf(searchTerm))

        val filteredUsers = usersList.filter { user ->
            user.name.contains(searchTerm, true)
        }

        uiState = uiState.copy(users = filteredUsers)
    }

    data class HomeUiState(
        val isLoading: Boolean = false,
        val users: List<User> = emptyList(),
        val errorMessage: String = "",
        val searchTerm: MutableState<String> = mutableStateOf("")
    )

    sealed class HomeUiEvent {
        object LoadUsers : HomeUiEvent()
    }

}