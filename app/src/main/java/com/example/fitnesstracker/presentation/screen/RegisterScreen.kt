package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.navigation.Screen
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledPasswordField
import com.example.fitnesstracker.presentation.ui.component.StyledTextField
import com.example.fitnesstracker.presentation.ui.component.StyledTopAppBar

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit
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
                    .padding(bottom = 16.dp),
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
                StyledPasswordField(
                    value = password,
                    onValueChange = {password = it},
                    showPassword = showPassword,
                    onShowPasswordChange = {showPassword = it}
                )
                var repeatedPassword by remember { mutableStateOf("") }
                var showRepeatedPassword by remember { mutableStateOf(false) }
                StyledPasswordField(
                    value = repeatedPassword,
                    onValueChange = {repeatedPassword = it},
                    showPassword = showRepeatedPassword,
                    onShowPasswordChange = {showRepeatedPassword = it},
                    label = stringResource(id = R.string.repeat_password)
                )

                val radioOptions = listOf(
                    stringResource(id = R.string.male),
                    stringResource(id = R.string.female),
                    stringResource(id = R.string.another_gender)
                )
                val (selectedOption, onOptionSelected) = mutableStateOf(radioOptions[0])
                Column(
                    modifier = Modifier
                        .selectableGroup()
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        text = stringResource(id = R.string.gender),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        letterSpacing = 0.sp
                    )
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(36.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null // null recommended for accessibility with screen readers
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StyledButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(
                        text = stringResource(id = R.string.button_register),
                        fontSize = 16.sp
                    )
                }

                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.privacy_policy_text))
                        append(" ")
                        withLink(
                            LinkAnnotation.Clickable(
                                tag = "some tag",
                                TextLinkStyles(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Blue)),
                                linkInteractionListener = null
                            )
                        ) {
                            append(stringResource(id = R.string.privacy_policy))
                        }
                        append(" ")
                        append(stringResource(R.string.user_agreement_text))
                        append(" ")
                        withLink(
                            LinkAnnotation.Clickable(
                                tag = "some tag",
                                TextLinkStyles(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Blue)),
                                linkInteractionListener = null
                            )
                        ) {
                            append(stringResource(id = R.string.user_agreement))
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center
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