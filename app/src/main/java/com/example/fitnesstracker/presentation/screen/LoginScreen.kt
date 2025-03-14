package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.navigation.Screen
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledImage
import com.example.fitnesstracker.presentation.ui.component.StyledPasswordField
import com.example.fitnesstracker.presentation.ui.component.StyledTextField
import com.example.fitnesstracker.presentation.ui.component.StyledTopAppBar

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {

    Scaffold(
        topBar = {
            StyledTopAppBar(
                title = stringResource(id = R.string.top_bar_login),
                contentDescription = "Back Arrow Image"
            )
        },
        bottomBar = {},
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StyledImage(
                size = 300.dp,
                painter = painterResource(id = R.drawable.main_screen_image),
                contentDescription = "Fitness Tracker Image"
            )
            var login by remember { mutableStateOf("") }
            StyledTextField(
                value = login,
                onValueChange = {login = it},
                label = stringResource(id = R.string.login)
            )
            var password by remember { mutableStateOf("") }
            StyledPasswordField(
                value = password,
                onValueChange = {password = it},
            )
            StyledButton(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(width = 320.dp, height = 48.dp),
                onClick = {}
            ) {
                Text(
                    text = stringResource(id = R.string.button_sign_in),
                    fontSize = 16.sp
                )
            }
        }
    }


}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen {}
}