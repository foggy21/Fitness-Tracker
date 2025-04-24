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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.presentation.ui.component.LinkTextPart
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledClickableText
import com.example.fitnesstracker.presentation.ui.component.StyledTextField
import com.example.fitnesstracker.presentation.ui.theme.Burgundy
import com.example.fitnesstracker.res.AppStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = AppStrings.PROFILE,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )},
                actions = {
                    StyledClickableText(
                        modifier = modifier
                            .padding(end = 8.dp),
                         textParts = listOf(
                             LinkTextPart(
                                 text = AppStrings.SAVE,
                                 isLink = true,
                                 onClick = {}
                             )
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700
                    )
                },
                windowInsets = WindowInsets(0,0,0,0)
            )
        },
        bottomBar = {},
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var login by remember { mutableStateOf("") }
                StyledTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = AppStrings.LOGIN
                )
                var name by remember { mutableStateOf("") }
                StyledTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = AppStrings.NAME_OR_NICKNAME
                )
            }
            Spacer(modifier = modifier
                .height(36.dp))
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                StyledClickableText(
                    textParts = listOf(
                        LinkTextPart(
                            text = AppStrings.CHANGE_PASSWORD,
                            isLink = true,
                            onClick = {}
                        )
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }

            Column (
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                StyledButton(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Burgundy
                    )
                ) {
                    Text(
                        text = AppStrings.LOG_OUT,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen()
}