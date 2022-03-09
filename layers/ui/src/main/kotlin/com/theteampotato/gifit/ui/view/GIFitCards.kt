package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GIFitCard(id: Long, text: String, iconImageVector: ImageVector, colorFilter: ColorFilter, onClicked: () -> Unit = {}, onFavoriteClicked: (Long) -> Unit = {}) {
    val horizontalPadding = 25.dp

    Box(
        Modifier
            .clickable { onClicked() }
            .padding(top = 10.dp, bottom = 10.dp, start = horizontalPadding, end = horizontalPadding)
            .fillMaxWidth()) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 6.dp, end = 6.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp), textAlign = TextAlign.Center, text = text)
            }
        }
        CardIcon(id = id, modifier = Modifier.align(Alignment.TopEnd), iconImageVector = iconImageVector, colorFilter, onClick = onFavoriteClicked)
    }
}

@Composable
private fun CardIcon(id: Long, modifier: Modifier = Modifier, iconImageVector: ImageVector, colorFilter: ColorFilter, onClick: (Long) -> Unit) {
    val isFavorite = remember { mutableStateOf(false) }

    Button(
        modifier = modifier.requiredSize(35.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onBackground
        ),
        onClick = {
            onClick(id)
            isFavorite.value = !isFavorite.value
        },
        shape = CircleShape
    ) {
        Image(
            modifier = Modifier.requiredSize(25.dp),
            contentDescription = null,
            imageVector = iconImageVector,
            colorFilter = colorFilter
        )
    }
}

@Composable
fun GIFitSplashCard() {
    Card(
        modifier = Modifier
            .requiredWidth(180.dp)
            .requiredHeight(215.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GIFitLogoText()
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 28.dp)
            )
            InfoTip(origin = "Sentence", translated = "Cümle")
        }
    }
}

@Composable
private fun GIFitLogoText() {
    val string = buildAnnotatedString {
        val value = "GIFit"

        append(value)
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            ), start = 0, end = 3
        )
        addStyle(
            style = SpanStyle(fontSize = 48.sp, color = MaterialTheme.colors.onSurface),
            start = 3,
            end = value.length
        )
    }

    Text(text = string)
}

@Composable
@Preview
fun PreviewGIFitSplashCard() = GIFitSplashCard()

@Composable
@Preview
fun PreviewGifitCard() = GIFitCard(0L,"Example", iconImageVector = Icons.Outlined.Favorite, colorFilter = ColorFilter.tint(Color.Red)) {}