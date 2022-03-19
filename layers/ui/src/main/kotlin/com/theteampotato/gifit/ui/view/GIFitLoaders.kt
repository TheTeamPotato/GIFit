package com.theteampotato.gifit.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun GIFitLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("spinner.json"))

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray.copy(alpha = 0.5f)) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, alignment = Alignment.Center)
    }
}

@Composable
fun GIFitTranslateModelLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("spinner.json"))

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray.copy(alpha = 0.5f)) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, alignment = Alignment.Center)
        Snackbar(modifier = Modifier
            .wrapContentSize(align = Alignment.BottomCenter)
            .padding(start = 8.dp, end = 8.dp, bottom = 20.dp)
        ) { Text(text = "Please wait while downloading translation model!") }
    }
}