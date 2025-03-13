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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.screen.viewmodel.PasswordView
import com.example.fitnesstracker.presentation.ui.theme.Grey
import com.example.fitnesstracker.presentation.ui.theme.LightGrey
import com.example.fitnesstracker.presentation.ui.theme.Primary

@Composable
fun StyledPasswordField(
    modifier: Modifier = Modifier,
    passwordView: PasswordView = viewModel(),
    label: String = stringResource(id = R.string.password),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    OutlinedTextField(
        modifier = Modifier
            .width(320.dp)
            .padding(bottom = 16.dp),
        value = passwordView.password,
        onValueChange = passwordView::updatePassword,
        label = { Text(
            label,
            fontSize = 16.sp,
            color = Grey,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp
        ) },
        singleLine = true,
        visualTransformation = if (passwordView.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = LightGrey,
            focusedBorderColor = Primary
        ),
        trailingIcon = {
            val image = if (passwordView.showPassword)
                painterResource(id = R.drawable.visibility)
            else painterResource(id = R.drawable.visibility_off)

            // Localized description for accessibility services
            val description = if (passwordView.showPassword) "Hide password" else "Show password"

            // Toggle button to hide or display password
            IconButton(onClick = {passwordView.showPassword = !passwordView.showPassword}){
                Icon(painter = image, description)
            }
        }
    )
}
