package com.giussepr.ceiba.ui.presentation.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {


    fun onUiEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            is HomeUiEvent.LoadUsers -> { /* Load users */ }
        }
    }

    sealed class HomeUiEvent {
        object LoadUsers : HomeUiEvent()
    }

}