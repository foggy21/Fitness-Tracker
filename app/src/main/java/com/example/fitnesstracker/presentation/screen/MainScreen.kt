package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.fitnesstracker.presentation.ui.component.StyledImage
import com.example.fitnesstracker.presentation.ui.theme.Grey

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledImage(
            modifier = Modifier
                .padding(top = 82.dp),
            size = 400.dp,
            painter = painterResource(id = R.drawable.main_screen_image),
            contentDescription = "Fitness Tracker Image"
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 30.dp),
            text = stringResource(id = R.string.app_title),
            fontSize = 24.sp,
            lineHeight = 35.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(bottom = 30.dp),
            text = stringResource(id = R.string.app_subtitle),
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            color = Grey,
        )
        StyledButton(
            onClick = {}
        ) {
            Text(
                text = stringResource(id = R.string.button_register),
                fontSize = 16.sp
            )
        }
        Text(
            buildAnnotatedString {
                withLink(
                    LinkAnnotation.Clickable(
                        tag = "some tag",
                        TextLinkStyles(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Blue)),
                        linkInteractionListener = null
                    )
                ) {
                    append(stringResource(id = R.string.sign_in))
                }
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
        )

    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen {}
}