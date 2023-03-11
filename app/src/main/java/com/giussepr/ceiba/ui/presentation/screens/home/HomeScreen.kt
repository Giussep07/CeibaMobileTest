package com.giussepr.ceiba.ui.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.ui.presentation.widget.CeibaTopAppBar
import com.giussepr.ceiba.ui.presentation.widget.UserCardItem

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.onUiEvent(HomeViewModel.HomeUiEvent.LoadUsers)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CeibaTopAppBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(paddingValues)
        ) {
            when (val state = viewModel.uiState.collectAsState().value) {
                is HomeViewModel.HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is HomeViewModel.HomeUiState.Success -> {
                    HomeScreenContent(userList = state.users)
                }

                is HomeViewModel.HomeUiState.Error -> {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = state.message,
                        style = MaterialTheme.typography.h6,
                        color = Red
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(userList: List<User>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(userList) {
            UserCardItem(name = it.name, phone = it.phone, email = it.email) {}
        }
    }
}