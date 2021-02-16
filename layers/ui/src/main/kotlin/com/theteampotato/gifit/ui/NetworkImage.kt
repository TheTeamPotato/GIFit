package com.theteampotato.gifit.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import dev.chrisbanes.accompanist.coil.AmbientImageLoader
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    transformation: Transformation = RoundedCornersTransformation(0f),
    placeholderColor: Color? = MaterialTheme.colors.secondary
) {
    CoilImage(
        data = url,
        modifier = modifier,
        contentScale = contentScale,
        fadeIn = true,
        requestBuilder = {
            // transformations(RoundedCornersTransformation(30f))
            transformations(transformation)
        },
        loading = {
            if (placeholderColor != null) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(placeholderColor)
                )
            }
        }
    )
}

@Composable
fun ProvideImageLoader(content: @Composable () -> Unit) {
    val context = AmbientContext.current
    val loader = remember(context) {
        ImageLoader.Builder(context)
            .componentRegistry {
                if (SDK_INT >= 28)
                    add(ImageDecoderDecoder())
                else
                    add(GifDecoder())
            }.build()
    }
    Providers(AmbientImageLoader provides loader, content = content)
}