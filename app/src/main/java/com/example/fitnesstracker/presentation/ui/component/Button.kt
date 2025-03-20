package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.presentation.ui.theme.Primary

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth(0.9f),
        onClick = onClick,
        shape = RoundedCornerShape(size = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary
        )
    ) {
        content()
    }
}