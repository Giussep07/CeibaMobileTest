package com.giussepr.ceiba.ui.presentation.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object Home : AppScreens("home")
    object PublicationsScreen : AppScreens("publications_screen")

    fun withArgs(vararg args: String): String {
        return "$route?${args.joinToString("?")}"
    }
}
