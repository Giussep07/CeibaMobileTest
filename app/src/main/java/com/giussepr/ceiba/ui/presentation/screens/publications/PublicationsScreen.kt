package com.giussepr.ceiba.ui.presentation.screens.publications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.giussepr.ceiba.R
import com.giussepr.ceiba.ui.presentation.theme.CeibaTheme
import com.giussepr.ceiba.ui.presentation.widget.CeibaTopAppBar
import com.giussepr.ceiba.ui.presentation.widget.PublicationCardItem

@Composable
@Preview
fun PublicationsScreenPreview() {
    CeibaTheme {
        PublicationsScreen(rememberNavController())
    }
}

@Composable
fun PublicationsScreen(navController: NavHostController, viewModel: PublicationsViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        topBar = {
            CeibaTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.publications)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                // User name
                Text(
                    text = viewModel.uiState.name,
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(4.dp))
                // User phone
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Phone,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = stringResource(id = R.string.phone_icon)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = viewModel.uiState.phone, style = MaterialTheme.typography.body1)
                }
                Spacer(modifier = Modifier.height(4.dp))
                // User email
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Email,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = stringResource(id = R.string.phone_icon)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = viewModel.uiState.email, style = MaterialTheme.typography.body1)
                }
                // Publications
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.publications),
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.primary)
                )
            }
            val list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(list) {
                    PublicationCardItem(
                        title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
                    )
                }
            }

        }

    }
}