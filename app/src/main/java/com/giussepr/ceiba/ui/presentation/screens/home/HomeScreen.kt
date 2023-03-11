package com.giussepr.ceiba.ui.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.giussepr.ceiba.ui.presentation.widget.CeibaTopAppBar
import com.giussepr.ceiba.ui.presentation.widget.UserCardItem

@Composable
fun HomeScreen(navController: NavHostController) {
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
            val list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(list) {
                    UserCardItem()
                }
            }
        }
    }
}