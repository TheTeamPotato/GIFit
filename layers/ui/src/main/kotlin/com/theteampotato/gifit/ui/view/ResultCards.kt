package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ResultCard(imageURL: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 6.dp, end = 6.dp)
        ) {
            ContentCard(imageURL = imageURL)
        }
        FavoriteCardIcon(modifier = Modifier.align(Alignment.TopEnd))
    }
}

@Composable
private fun FavoriteCardIcon(modifier: Modifier = Modifier) {
    val isFavorite = remember { mutableStateOf(false) }

    val imageVector = if (isFavorite.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder
    val colorFilter = if (isFavorite.value) ColorFilter.tint(Color.Red) else null

    Button(
        modifier = modifier.size(35.dp),
        colors = ButtonConstants.defaultButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onBackground
        ),
        onClick = { isFavorite.value = !isFavorite.value },
        shape = CircleShape
    ) {
        Image(modifier = Modifier.size(25.dp), imageVector = imageVector, colorFilter = colorFilter)
    }
}

@Composable
private fun ContentCard(modifier: Modifier = Modifier, imageURL: String) {
    val horizontalPaddingInDp = 24.dp

    Card(modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            GIFitImage(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = horizontalPaddingInDp),
                imageURL = imageURL
            )
            Divider(
                color = Color(209, 209, 209),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPaddingInDp)
            )
            ListenableInfoTip(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .padding(horizontal = horizontalPaddingInDp),
                origin = "Bana bir şey sor.",
                translated = "Cümle"
            )
        }
    }
}

@Composable
private fun GIFitImage(modifier: Modifier = Modifier, imageURL: String) {
    Card(modifier.fillMaxWidth().height(250.dp)) {
        CoilImage(
            data = imageURL,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
@Preview
private fun PreviewResultCard() =
    ResultCard(imageURL = "https://media3.giphy.com/media/XFuQ4InwtXBE4DDPHM/giphy.gif")