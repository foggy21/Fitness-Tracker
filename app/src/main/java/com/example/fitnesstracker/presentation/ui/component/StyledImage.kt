package com.example.fitnesstracker.presentation.ui.component

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R

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