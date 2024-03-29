package com.theteampotato.gifit.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun ProvideImageLoader(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val loader = remember(context) {
        ImageLoader.Builder(context)
            .componentRegistry {
                if (SDK_INT >= 28)
                    add(ImageDecoderDecoder(context))
                else
                    add(GifDecoder())
            }.build()
    }
    CompositionLocalProvider(LocalImageLoader provides loader, content = content)
}