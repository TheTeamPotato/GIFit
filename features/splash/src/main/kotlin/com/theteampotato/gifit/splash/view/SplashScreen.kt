package com.theteampotato.gifit.splash.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.theteampotato.gifit.ui.view.GIFitSplashCard

@Composable
fun SplashScreen() {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        GIFitSplashCard()
    }
}