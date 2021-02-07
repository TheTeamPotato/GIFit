package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GIFitSplashCard() {
    Card(modifier = Modifier
        .width(180.dp)
        .height(215.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GIFitLogoText()
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 28.dp))
            InfoTip(origin = "Sentence", translated = "Cümle")
        }
    }
}

@Composable
private fun GIFitLogoText() {
    val string = annotatedString {
        val value = "GIFit"

        append(value)
        addStyle(style = SpanStyle(color = MaterialTheme.colors.onSurface, fontSize = 48.sp, fontWeight = FontWeight.Bold), start = 0, end = 3)
        addStyle(style = SpanStyle(fontSize = 48.sp, color = MaterialTheme.colors.onSurface), start = 3, end = value.length)
    }

    Text(text = string)
}

@Composable
@Preview
fun PreviewGIFitSplashCard() = GIFitSplashCard()