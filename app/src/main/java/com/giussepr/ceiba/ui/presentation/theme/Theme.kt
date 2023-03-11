package com.giussepr.ceiba.ui.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColors(
    primary = DarkGreen,
    primaryVariant = DarkGreenVariant,
    secondary = LightGreen,
    secondaryVariant = LightGreenVariant,
)

private val LightColorScheme = lightColors(
    primary = DarkGreen,
    primaryVariant = DarkGreenVariant,
    secondary = LightGreen,
    secondaryVariant = LightGreenVariant,
    background = BackgroundColor,
)

@Composable
fun CeibaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = LightColorScheme,
        typography = Typography,
        content = content
    )
}