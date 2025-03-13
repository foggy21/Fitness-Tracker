package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstracker.presentation.screen.viewmodel.TextView
import com.example.fitnesstracker.presentation.ui.theme.Grey
import com.example.fitnesstracker.presentation.ui.theme.LightGrey
import com.example.fitnesstracker.presentation.ui.theme.Primary

@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    textView: TextView = viewModel(),
    label: String = ""
) {
    OutlinedTextField(
        label = {  Text(
            text = label,
            fontSize = 16.sp,
            color = Grey,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp)},
        modifier = Modifier
            .width(320.dp)
            .padding(bottom = 16.dp),
        value = textView.text,
        onValueChange = textView::updateText,
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = LightGrey,
            focusedBorderColor = Primary
        )
    )
}
