package com.giussepr.ceiba.ui.presentation.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.giussepr.ceiba.R

@Composable
fun CeibaTopAppBar(
    navController: NavHostController,
    title: String = stringResource(id = R.string.mobile_test),
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.navigate_back)
                    )
                }
            }
        }
    )
}