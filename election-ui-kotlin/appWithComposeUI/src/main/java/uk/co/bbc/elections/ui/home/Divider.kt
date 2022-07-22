package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(
    thickness: Dp = 1.dp,
    colour: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
) = Box(
    Modifier
        .fillMaxWidth()
        .height(thickness)
        .background(colour)
)

@Composable
fun VerticalDivider(
    thickness: Dp = 1.dp,
    colour: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
) = Box(
    Modifier
        .width(thickness)
        .fillMaxHeight()
        .background(colour)
)
