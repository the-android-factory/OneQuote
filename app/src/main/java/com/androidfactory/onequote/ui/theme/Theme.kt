package com.androidfactory.onequote.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple,
    primaryVariant = DarkPurple,
    secondary = Pink,
    background = DarkPurple,
    surface = Surface,
    onSurface = OnSurface,
    onBackground = OnBackground
)

@Composable
fun OneQuoteTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}