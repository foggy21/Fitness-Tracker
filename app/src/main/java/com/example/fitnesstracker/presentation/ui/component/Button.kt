package com.example.fitnesstracker.presentation.ui.component

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
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(size = 15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary

        )
    ) {
        content()
    }
}