package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.presentation.ui.theme.Primary

@Composable
fun StyledActionButton(
    onClick: () -> Unit,
    iconRes: Int,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = Primary,
        shape = RoundedCornerShape(60.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            StyledImage(
                painter = painterResource(id = iconRes),
                contentDescription = "Content Description",
                size = 24.dp
            )
        }
    }
}