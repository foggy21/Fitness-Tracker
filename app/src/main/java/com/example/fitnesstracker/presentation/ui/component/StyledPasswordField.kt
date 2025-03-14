package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.ui.theme.Grey
import com.example.fitnesstracker.presentation.ui.theme.LightGrey
import com.example.fitnesstracker.presentation.ui.theme.Primary

@Composable
fun StyledPasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    showPassword: Boolean = false,
    onShowPasswordChange: (Boolean) -> Unit = {},
    label: String = stringResource(id = R.string.password)
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(320.dp)
            .padding(bottom = 16.dp),
        label = { Text(
            label,
            fontSize = 16.sp,
            color = Grey,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp
        ) },
        singleLine = true,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = LightGrey,
            focusedBorderColor = Primary
        ),
        trailingIcon = {
            val image = if (showPassword)
                painterResource(id = R.drawable.visibility)
            else painterResource(id = R.drawable.visibility_off)
            val description = if (showPassword) "Hide password" else "Show password"
            IconButton(onClick = {onShowPasswordChange(!showPassword)}){
                Icon(painter = image, description)
            }
        }
    )
}
