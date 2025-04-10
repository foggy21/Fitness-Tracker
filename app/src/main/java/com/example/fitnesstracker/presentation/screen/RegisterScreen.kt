package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.presentation.navigation.NavigationCallback
import com.example.fitnesstracker.presentation.navigation.Screen
import com.example.fitnesstracker.presentation.state.AuthenticationEvent
import com.example.fitnesstracker.presentation.ui.component.GenderSelection
import com.example.fitnesstracker.presentation.ui.component.LinkTextPart
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledClickableText
import com.example.fitnesstracker.presentation.ui.component.StyledPasswordField
import com.example.fitnesstracker.presentation.ui.component.StyledTextField
import com.example.fitnesstracker.presentation.ui.component.StyledTopAppBar
import com.example.fitnesstracker.presentation.ui.theme.Primary
import com.example.fitnesstracker.res.AppStrings
import com.example.fitnesstracker.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>(),
    onNavigateTo: NavigationCallback = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.events.collect{ event ->
            when (event) {
                is AuthenticationEvent.Success -> {
                    onNavigateTo(Screen.Login)
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
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StyledTopAppBar(
                onNavigationTo = onNavigateTo,
                title = AppStrings.TOP_BAR_SIGN_UP,
                contentDescription = "Back Arrow Image"
            )
        },
        bottomBar = {},
        snackbarHost = { SnackbarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column (
                modifier = Modifier
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

                StyledTextField(
                    value = uiState.nickname,
                    onValueChange = { viewModel.updateNickname(it) },
                    label = AppStrings.NAME_OR_NICKNAME
                )

                StyledPasswordField(
                    value = uiState.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    showPassword = uiState.showPassword,
                    onShowPasswordChange = { viewModel.togglePasswordVisibility() },
                    isError = uiState.passwordError != null,
                    errorMessage = uiState.passwordError
                )

                StyledPasswordField(
                    value = uiState.repeatedPassword,
                    onValueChange = { viewModel.updateRepeatedPassword(it) },
                    showPassword = uiState.showRepeatedPassword,
                    onShowPasswordChange = { viewModel.toggleRepeatedPasswordVisibility() },
                    label = AppStrings.REPEAT_PASSWORD,
                    isError = uiState.repeatedPasswordError != null,
                    errorMessage = uiState.repeatedPasswordError
                )
            }

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .selectableGroup()
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp, bottom = 8.dp),
                        text = AppStrings.GENDER,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        letterSpacing = 0.sp
                    )

                    GenderSelection(
                        viewModel = viewModel
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StyledButton(
                        onClick = { viewModel.register() },
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
                    StyledClickableText(
                        textParts = listOf(
                            LinkTextPart(
                                text = AppStrings.PRIVACY_POLICY_TEXT
                            ),
                            LinkTextPart(
                                text = AppStrings.PRIVACY_POLICY,
                                isLink = true,
                                onClick = {}
                            ),
                            LinkTextPart(
                                text = AppStrings.USER_AGREEMENT_TEXT
                            ),
                            LinkTextPart(
                                text = AppStrings.USER_AGREEMENT,
                                isLink = true,
                                onClick = {}
                            )
                        ),
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
    RegisterScreen {}
}