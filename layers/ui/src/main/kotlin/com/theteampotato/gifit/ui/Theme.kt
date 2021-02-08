package com.theteampotato.gifit.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.dp

@Composable
fun GIFitTheme(content: @Composable () -> Unit) {
    ProvideImageLoader {
        MaterialTheme(
            colors = colors,
            typography = Typography(fontFamily),
            shapes = Shapes(medium = RoundedCornerShape(20.dp)),
            content = content
        )
    }
}

private val colors = Colors(
    primary = Color.White,
    primaryVariant = Color(red = 240, green = 240, blue = 240),
    onPrimary = Color.Black,
    secondary = Color.Black,
    secondaryVariant = Color(red = 131, green = 131, blue = 131),
    onSecondary = Color.White,
    background = Color(red = 240, green = 240, blue = 240),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color.Red,
    onError = Color.White,
    isLight = true
)

private val fontFamily = fontFamily(
    fonts = listOf(
        ResourceFont(
            resId = R.font.roboto_regular,
            weight = FontWeight.W400,
            style = FontStyle.Normal
        ),
        ResourceFont(
            resId = R.font.roboto_bold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        )
    )
)