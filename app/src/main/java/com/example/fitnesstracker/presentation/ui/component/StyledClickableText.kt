package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.example.fitnesstracker.presentation.ui.theme.Primary

data class LinkTextPart(
    val text: String,
    val isLink: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable
fun StyledClickableText(
    textParts: List<LinkTextPart>,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    linkColor: Color = Primary,
    textColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text = buildAnnotatedString {
            textParts.forEachIndexed { index, part ->
                if (part.isLink) {
                    pushStringAnnotation(
                        tag = "url_$index",
                        annotation = "url",
                    )

                    withStyle(SpanStyle(color = linkColor)){
                        append(part.text)
                    }
                    pop()
                } else {
                    withStyle(SpanStyle(color = textColor)){
                        append(part.text)
                    }
                }

                if (index < textParts.size - 1) {
                    append(" ")
                }
            }
        },
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        textAlign = textAlign,
        modifier = modifier
    )
}