package com.giussepr.ceiba.ui.presentation.screens.publications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giussepr.ceiba.domain.model.Publication
import com.giussepr.ceiba.domain.model.fold
import com.giussepr.ceiba.domain.usecase.GetUserPublicationsUseCase
import com.giussepr.ceiba.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PublicationsViewModel @Inject constructor(
    private val getUserPublicationsUseCase: GetUserPublicationsUseCase,
    private val dispatcherProvider: DispatcherProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(PublicationsUiState())
        private set

    init {
        savedStateHandle.get<Int>("userId")?.let { userId ->
            uiState = uiState.copy(userId = userId)
        }
        savedStateHandle.get<String>("name")?.let { name ->
            uiState = uiState.copy(name = name)
        }
        savedStateHandle.get<String>("phone")?.let { phone ->
            uiState = uiState.copy(phone = phone)
        }
        savedStateHandle.get<String>("email")?.let { email ->
            uiState = uiState.copy(email = email)
        }
    }

    private fun onLoadPublications() {
        getUserPublicationsUseCase(uiState.userId).map { result ->
            result.fold(
                onSuccess = { publications ->
                    uiState = uiState.copy(publications = publications, isLoading = false)
                },
                onFailure = {
                    uiState = uiState.copy(errorMessage = it.message, isLoading = false)
                })
        }.onStart { uiState = uiState.copy(isLoading = true) }
            .flowOn(dispatcherProvider.io).launchIn(viewModelScope)
    }

    fun onUiEvent(uiEvent: PublicationsUiEvent) {
        when (uiEvent) {
            is PublicationsUiEvent.LoadPublications -> onLoadPublications()
        }
    }

    sealed class PublicationsUiEvent {
        object LoadPublications : PublicationsUiEvent()
    }

    data class PublicationsUiState(
        val userId: Int = -1,
        val name: String = "",
        val phone: String = "",
        val email: String = "",
        val isLoading: Boolean = false,
        val publications: List<Publication> = emptyList(),
        val errorMessage: String = ""
    )
}