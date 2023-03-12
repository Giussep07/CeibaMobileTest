package com.giussepr.ceiba.ui.presentation.screens.publications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublicationsViewModel @Inject constructor(
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

    data class PublicationsUiState(
        val userId: Int = -1,
        val name: String = "",
        val phone: String = "",
        val email: String = ""
    )
}