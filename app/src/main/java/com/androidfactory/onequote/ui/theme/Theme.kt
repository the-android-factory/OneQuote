package com.androidfactory.onequote.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple,
    primaryVariant = DarkPurple,
    secondary = Pink,
    background = Color.Black,
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