package com.theteampotato.gifit.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext

import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.intercept.Interceptor
import coil.request.ImageResult
import coil.size.PixelSize
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation

import dev.chrisbanes.accompanist.coil.AmbientImageLoader
import dev.chrisbanes.accompanist.coil.CoilImage

import okhttp3.HttpUrl

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
            //transformations(RoundedCornersTransformation(30f))
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
                //add(UnsplashSizingInterceptor)

                if (SDK_INT >= 28)
                    add(ImageDecoderDecoder())
                else
                    add(GifDecoder())
            }.build()
    }
    Providers(AmbientImageLoader provides loader, content = content)
}

/**
 * A Coil [Interceptor] which appends query params to Unsplash urls to request sized images.
 */
@OptIn(ExperimentalCoilApi::class)
object UnsplashSizingInterceptor : Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data
        val size = chain.size
        if (data is String &&
            data.startsWith("https://images.unsplash.com/photo-") &&
            size is PixelSize &&
            size.width > 0 &&
            size.height > 0
        ) {
            val url = HttpUrl.parse(data)!!
                .newBuilder()
                .addQueryParameter("w", size.width.toString())
                .addQueryParameter("h", size.height.toString())
                .build()
            val request = chain.request.newBuilder().data(url).build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request)
    }
}