package com.theteampotato.gifit.splash.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.theteampotato.gifit.ui.GIFitSplashCard

@Composable
fun SplashScreen() {
    Surface(
        color = Color(red = 240, green = 240, blue = 240),
        modifier = Modifier.fillMaxSize()
    ) {
        GIFitSplashCard()
    }
}