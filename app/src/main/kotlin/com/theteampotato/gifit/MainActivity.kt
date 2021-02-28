package com.theteampotato.gifit

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent

import com.theteampotato.gifit.data.remote.GiphyService
import com.theteampotato.gifit.splash.view.SplashScreen
import com.theteampotato.gifit.translate.GoogleMLKitTranslator
import com.theteampotato.gifit.ui.GIFitTheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*

import timber.log.Timber

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    private var parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    @Inject
    lateinit var service: GiphyService

    @Inject
    lateinit var translator: GoogleMLKitTranslator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFitTheme {
                SplashScreen()
            }
        }

        launch {
            val response = service.search("Ask me anything")
            Timber.d("Response is ${response.data}")
        }

        translator.initialize()
        translator.destroy()
        launch {
            try {
                translator.translate(
                    value = "Siktir",
                    onSuccess = {
                        Timber.d("Translated text is $it")
                    }, onFailure = {
                        Timber.e("Exception is $it")
                    })
            } catch (e: Exception) {
                Timber.e("Fucking exception is $e")
            }
        }
    }

}

@Module
@InstallIn(SingletonComponent::class)
object MLKitTranslator {
    @Provides
    fun providesGoogleMLKitTranslator() = GoogleMLKitTranslator()
}