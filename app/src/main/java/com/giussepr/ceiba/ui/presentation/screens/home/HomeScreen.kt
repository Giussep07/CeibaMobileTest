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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.giussepr.ceiba.ui.presentation.navigation.AppScreens
import com.giussepr.ceiba.ui.presentation.widget.CeibaTopAppBar
import com.giussepr.ceiba.ui.presentation.widget.SearchTextField
import com.giussepr.ceiba.ui.presentation.widget.UserCardItem

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}

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
            if (viewModel.uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.testTag("circularProgress"))
                }
            }

            if (!viewModel.uiState.isLoading) {
                HomeScreenContent(viewModel, navController)
            }

            if (viewModel.uiState.users.isEmpty()) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "List is empty",
                    style = MaterialTheme.typography.h6
                )
            }

            if (!viewModel.uiState.isLoading && viewModel.uiState.errorMessage.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = viewModel.uiState.errorMessage,
                    style = MaterialTheme.typography.h6,
                    color = Red
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(viewModel: HomeViewModel, navController: NavHostController) {
    SearchTextField(
        viewModel.uiState.searchTerm,
        viewModel::onSearchTermChanged
    )
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(viewModel.uiState.users) {
            UserCardItem(name = it.name, phone = it.phone, email = it.email) {
                val route = AppScreens.PublicationsScreen.withArgs(
                    it.id.toString(),
                    it.name,
                    it.phone,
                    it.email
                )

                navController.navigate(route)
            }
        }
    }
}