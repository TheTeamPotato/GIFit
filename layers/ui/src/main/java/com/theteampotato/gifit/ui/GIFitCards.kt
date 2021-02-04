package com.theteampotato.gifit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GIFitSplashCard() {
    Card(modifier = Modifier
        .width(200.dp)
        .height(250.dp),
        shape = RoundedCornerShape(20.dp)
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
        addStyle(style = SpanStyle(color = Color.Black, fontSize = 64.sp, fontWeight = FontWeight.Bold), start = 0, end = 3)
        addStyle(style = SpanStyle(fontSize = 64.sp, color = Color.Black), start = 3, end = value.length)
    }

    Text(text = string)
}

@Composable
private fun InfoTip(origin: String, translated: String) {
    Column {
        Text(text = origin, fontSize = 24.sp)
        Text(text = translated, fontSize = 18.sp, color = Color(red = 131, green = 131, blue = 131))
    }
}

@Composable
@Preview
fun PreviewGIFitSplashCard() = GIFitSplashCard()