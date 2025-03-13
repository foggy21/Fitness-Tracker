package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

@Composable
fun StyledImage (
    modifier: Modifier = Modifier,
    size: Dp,
    painter: Painter,
    contentDescription: String = "Content Description"
) {
    Image(
        modifier = Modifier
            .size(size),
        painter = painter,
        contentDescription = contentDescription
    )
}