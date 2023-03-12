package com.giussepr.ceiba.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.giussepr.ceiba.ui.presentation.screens.home.HomeScreen
import com.giussepr.ceiba.ui.presentation.screens.publications.PublicationsScreen
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
        composable(route = AppScreens.PublicationsScreen.route
            .plus("?{userId}")
            .plus("?{name}")
            .plus("?{phone}")
            .plus("?{email}"),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
            )
        ) {
            PublicationsScreen(navController)
        }
    }
}