package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

@Composable
fun StyledImage (
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = "Content Description",
    size: Dp,
) {
    Image(
        modifier = modifier
            .size(size),
        painter = painter,
        alignment = Alignment.Center,
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit
    )
}