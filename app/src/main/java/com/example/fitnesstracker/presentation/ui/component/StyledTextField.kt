package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.presentation.ui.theme.Grey
import com.example.fitnesstracker.presentation.ui.theme.LightGrey
import com.example.fitnesstracker.presentation.ui.theme.Primary

@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {  Text(
                text = label,
                fontSize = 16.sp,
                color = if (isError) MaterialTheme.colorScheme.error else Grey,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp)
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = if (isError) MaterialTheme.colorScheme.error else LightGrey,
                focusedBorderColor = if (isError) MaterialTheme.colorScheme.error else Primary
            )
        )

        AnimatedVisibility(
            visible = isError && errorMessage != null,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier
                    .padding(start = 16.dp)
            )
        }
    }
}
