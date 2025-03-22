package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.navigation.NavigationCallback
import com.example.fitnesstracker.presentation.ui.component.LinkTextPart
import com.example.fitnesstracker.presentation.ui.component.StyledButton
import com.example.fitnesstracker.presentation.ui.component.StyledClickableText
import com.example.fitnesstracker.presentation.ui.component.StyledImage
import com.example.fitnesstracker.presentation.ui.theme.Grey

@Composable
fun MainScreen(
    onNavigateTo: NavigationCallback = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            StyledImage(
                size = 400.dp,
                painter = painterResource(id = R.drawable.main_screen_image),
                contentDescription = "Fitness Tracker Image"
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.app_title_first),
                fontSize = 24.sp,
                lineHeight = 35.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Text(
                text = stringResource(id = R.string.app_title_second),
                fontSize = 24.sp,
                lineHeight = 35.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.app_subtitle),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Grey,
            )
            Spacer(modifier = Modifier.height(16.dp))
            StyledButton(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                onClick = {}
            ) {
                Text(
                    text = stringResource(id = R.string.button_register),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            StyledClickableText(
                textParts = listOf(
                    LinkTextPart(
                        text = stringResource(id = R.string.sign_in),
                        isLink = true,
                        onClick = {}
                    )
                ),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
            )
            Spacer(modifier = Modifier.weight(3f))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen {}
}