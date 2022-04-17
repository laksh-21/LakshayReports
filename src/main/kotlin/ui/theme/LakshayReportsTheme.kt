package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkPaletteColors = darkColors(
    primary = Color.White,
    primaryVariant = Color.White,
    background = Color.Black,
    surface = Color.Black
)

private val LightPaletteColors = lightColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.Black,
    secondaryVariant = Color.Black
)

@Composable
fun LakshayReportsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkPaletteColors else LightPaletteColors,
        shapes = MaterialTheme.shapes,
        typography = MaterialTheme.typography,
        content = content
    )
}