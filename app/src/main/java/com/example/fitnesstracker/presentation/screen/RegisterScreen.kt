package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.navigation.NavigationCallback
import com.example.fitnesstracker.presentation.ui.component.Gender
import com.example.fitnesstracker.presentation.ui.component.GenderSelection
import com.example.fitnesstracker.presentation.ui.component.LinkTextPart
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledClickableText
import com.example.fitnesstracker.presentation.ui.component.StyledPasswordField
import com.example.fitnesstracker.presentation.ui.component.StyledTextField
import com.example.fitnesstracker.presentation.ui.component.StyledTopAppBar
import com.example.fitnesstracker.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onNavigateTo: NavigationCallback = {}
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StyledTopAppBar(
                title = stringResource(id = R.string.top_bar_sign_up),
                contentDescription = "Back Arrow Image"
            )
        },
        bottomBar = {},
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
                    value = viewModel.login.value,
                    onValueChange = { viewModel.updateLogin(it) },
                    label = stringResource(R.string.login),
                    isError = viewModel.loginError.value != null,
                    errorMessage = viewModel.loginError.value
                )

                StyledTextField(
                    value = viewModel.nickname.value,
                    onValueChange = { viewModel.updateNickname(it) },
                    label = stringResource(R.string.name_or_nickname)
                )

                StyledPasswordField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.updatePassword(it) },
                    showPassword = viewModel.showPassword.value,
                    onShowPasswordChange = { viewModel.togglePasswordVisibility() },
                    isError = viewModel.passwordError.value != null,
                    errorMessage = viewModel.passwordError.value
                )

                StyledPasswordField(
                    value = viewModel.repeatedPassword.value,
                    onValueChange = { viewModel.updateRepeatedPassword(it) },
                    showPassword = viewModel.showRepeatedPassword.value,
                    onShowPasswordChange = { viewModel.toggleRepeatedPasswordVisibility() },
                    label = stringResource(id = R.string.repeat_password),
                    isError = viewModel.repeatedPasswordError.value != null,
                    errorMessage = viewModel.repeatedPasswordError.value
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
                        text = stringResource(id = R.string.gender),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        letterSpacing = 0.sp
                    )

                    GenderSelection(
                        selectedGender = Gender.Male
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StyledButton(
                        onClick = { viewModel.register() }
                    ) {
                        Text(
                            text = stringResource(id = R.string.button_register),
                            fontSize = 16.sp
                        )
                    }
                    StyledClickableText(
                        textParts = listOf(
                            LinkTextPart(
                                text = stringResource(id = R.string.privacy_policy_text)
                            ),
                            LinkTextPart(
                                text = stringResource(id = R.string.privacy_policy),
                                isLink = true,
                                onClick = {}
                            ),
                            LinkTextPart(
                                text = stringResource(id = R.string.user_agreement_text)
                            ),
                            LinkTextPart(
                                text = stringResource(id = R.string.user_agreement),
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