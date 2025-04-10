package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.navigation.NavigationCallback
import com.example.fitnesstracker.presentation.navigation.Screen
import com.example.fitnesstracker.presentation.state.AuthenticationEvent
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledImage
import com.example.fitnesstracker.presentation.ui.component.StyledPasswordField
import com.example.fitnesstracker.presentation.ui.component.StyledTextField
import com.example.fitnesstracker.presentation.ui.component.StyledTopAppBar
import com.example.fitnesstracker.presentation.ui.theme.Primary
import com.example.fitnesstracker.res.AppStrings
import com.example.fitnesstracker.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    onNavigateTo: NavigationCallback = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.events.collect{ event ->
            when(event) {
                is AuthenticationEvent.Success -> {
                    onNavigateTo(Screen.Activity)
                }
                is AuthenticationEvent.Error -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            StyledTopAppBar(
                onNavigationTo = onNavigateTo,
                title = AppStrings.TOP_BAR_LOGIN,
                contentDescription = "Back Arrow Image"
            )
        },
        bottomBar = {},
        snackbarHost = { SnackbarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                StyledImage(
                    painter = painterResource(id = R.drawable.main_screen_image),
                    contentDescription = "Fitness Tracker Image",
                    size = 300.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .aspectRatio(1f)
                )
            }

            Column (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                StyledTextField(
                    value = uiState.login,
                    onValueChange = { viewModel.updateLogin(it) },
                    label = AppStrings.LOGIN,
                    isError = uiState.loginError != null,
                    errorMessage = uiState.loginError
                )

                StyledPasswordField(
                    value = uiState.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    showPassword = uiState.showPassword,
                    onShowPasswordChange = { viewModel.togglePasswordVisibility() },
                    isError = uiState.passwordError != null,
                    errorMessage = uiState.passwordError
                )

                Spacer(modifier = Modifier.weight(1f))

                StyledButton(
                    onClick = { viewModel.login() },
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp),
                            color = Primary
                        )
                    } else {
                        Text(
                            text = AppStrings.BUTTON_SIGN_IN,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(3f))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen {}
}