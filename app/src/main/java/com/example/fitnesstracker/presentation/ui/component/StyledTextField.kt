package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {  Text(
            text = label,
            fontSize = 16.sp,
            color = Grey,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp)
        },
        modifier = modifier
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = LightGrey,
            focusedBorderColor = Primary
        )
    )
}
