package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoTip(modifier: Modifier = Modifier, origin: String, translated: String) {
    Column(modifier) {
        Text(text = origin, fontSize = 18.sp)
        Text(text = translated, fontSize = 12.sp, color = MaterialTheme.colors.secondaryVariant)
    }
}

@Composable
fun ListenableInfoTip(modifier: Modifier = Modifier, origin: String, translated: String) {
    val sizeInDp = 35.dp

    Column(modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Button(
                modifier = Modifier.size(sizeInDp),
                colors = ButtonConstants.defaultButtonColors(
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onBackground
                ),
                onClick = {},
                shape = CircleShape
            ) {
                Image(modifier = Modifier.size(sizeInDp).padding(5.dp), imageVector = Icons.Rounded.VolumeUp)
            }
            Column {
                Text(text = origin, fontSize = 21.sp)
                Text(
                    text = translated,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.secondaryVariant
                )
            }
        }
    }
}