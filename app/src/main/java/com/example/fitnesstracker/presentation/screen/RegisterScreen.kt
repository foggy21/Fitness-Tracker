package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun RegisterScreen(
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
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var login by remember { mutableStateOf("")}
                StyledTextField(
                    value = login,
                    onValueChange = {login = it},
                    label = stringResource(R.string.login)
                )
                var name by remember { mutableStateOf("")}
                StyledTextField(
                    value = name,
                    onValueChange = {name = it},
                    label = stringResource(R.string.name_or_nickname)
                )
                var password by remember { mutableStateOf("") }
                var showPassword by remember { mutableStateOf(false) }
                var passwordError by remember { mutableStateOf<String?>(null) }

                val context = LocalContext.current

                fun getStringFromResources( resId: Int): String {
                    return context.getString(resId)
                }

                fun validatePassword(password: String): String? {
                    return when {
                        password.length < 8 -> getStringFromResources(R.string.error_password_length)
                        else -> null
                    }
                }

                StyledPasswordField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = validatePassword(it)
                    },
                    showPassword = showPassword,
                    onShowPasswordChange = {showPassword = it},
                    isError = passwordError != null,
                    errorMessage = passwordError
                )
                var repeatedPassword by remember { mutableStateOf("") }
                var showRepeatedPassword by remember { mutableStateOf(false) }
                var repeatedPasswordError by remember { mutableStateOf<String?>(null) }

                fun validateRepeatedPassword(password: String, repeatedPassword: String): String? {
                    return when {
                        repeatedPassword.isEmpty() -> getStringFromResources(R.string.error_repeated_password_empty)
                        password != repeatedPassword -> getStringFromResources(R.string.error_repeated_password_not_equal)
                        else -> null
                    }
                }

                StyledPasswordField(
                    value = repeatedPassword,
                    onValueChange = {
                        repeatedPassword = it
                        repeatedPasswordError = validateRepeatedPassword(password, it)
                    },
                    showPassword = showRepeatedPassword,
                    onShowPasswordChange = {showRepeatedPassword = it},
                    label = stringResource(id = R.string.repeat_password),
                    isError = repeatedPasswordError != null,
                    errorMessage = repeatedPasswordError
                )
            }

            Column(
                modifier = Modifier
                    .selectableGroup()
                    .fillMaxWidth()
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
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StyledButton(
                    onClick = {}
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
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
    RegisterScreen {}
}