package com.example.kopilka.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme()
private val DarkColors = darkColorScheme(
    background = Color(0xFF0E0F12),
    surface = Color(0xFF15171C)
)

@Composable
fun KopilkaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val scheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = scheme,
        typography = Typography(),
        content = content
    )
}
