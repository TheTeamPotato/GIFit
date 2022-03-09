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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ResultCard(modifier: Modifier = Modifier, imageURL: String, translatedText: String, isFavorite: Boolean, onFavoriteClicked: () -> Unit) {
    Box(modifier.fillMaxWidth()) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 6.dp, end = 6.dp)
        ) {
            ContentCard(imageURL = imageURL, translatedText = translatedText)
        }
        FavoriteCardIcon(modifier = Modifier.align(Alignment.TopEnd), isFavorite = isFavorite, onClick = onFavoriteClicked)
    }
}

@Composable
private fun FavoriteCardIcon(modifier: Modifier = Modifier, isFavorite: Boolean, onClick: () -> Unit) {
    val isFavoriteClicked = rememberSaveable { mutableStateOf(false) }

    val imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder
    val colorFilter = if (isFavorite) ColorFilter.tint(Color.Red) else null

    Button(
        modifier = modifier.requiredSize(35.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onBackground
        ),
        onClick = {
            isFavoriteClicked.value = !isFavoriteClicked.value
            onClick()
        },
        shape = CircleShape
    ) {
        Image(
            modifier = Modifier.requiredSize(25.dp),
            contentDescription = null,
            imageVector = imageVector,
            colorFilter = colorFilter
        )
    }
}

@Composable
private fun ContentCard(modifier: Modifier = Modifier, imageURL: String, translatedText: String) {
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
                origin = translatedText,
                translated = "Cümle"
            )
        }
    }
}

@Composable
private fun GIFitImage(modifier: Modifier = Modifier, imageURL: String) {
    Card(
        modifier
            .fillMaxWidth()
            .requiredHeight(220.dp)) {
        Image(
            painter = rememberImagePainter(imageURL),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview
fun PreviewResultCard() =
    ResultCard(isFavorite = false, imageURL = "https://media3.giphy.com/media/XFuQ4InwtXBE4DDPHM/giphy.gif", translatedText = "Bana bir sey sor", onFavoriteClicked = {})