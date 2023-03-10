package com.giussepr.ceiba.ui.presentation.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object Home : AppScreens("home")
}
