package com.giussepr.ceiba.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.giussepr.ceiba.ui.presentation.screens.home.HomeScreen
import com.giussepr.ceiba.ui.presentation.screens.splash.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.Home.route) {
            HomeScreen(navController)
        }
    }
}