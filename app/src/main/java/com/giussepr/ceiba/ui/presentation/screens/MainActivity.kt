package com.giussepr.ceiba.ui.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.giussepr.ceiba.ui.presentation.navigation.AppNavigation
import com.giussepr.ceiba.ui.presentation.theme.CeibaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CeibaTheme {
                AppNavigation(navController = rememberNavController())
            }
        }
    }
}