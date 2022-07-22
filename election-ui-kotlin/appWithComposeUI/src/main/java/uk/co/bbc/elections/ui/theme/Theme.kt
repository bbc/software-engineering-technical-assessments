package uk.co.bbc.elections.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFF44334),
    primaryVariant = Color(0xFFB9000A),
    secondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFF44334),
    primaryVariant = Color(0xFFB92020),
    secondary = Color.White
)

@Composable
fun ElectionsAssessmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
