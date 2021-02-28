package com.theteampotato.gifit

import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

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

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    private var parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFitTheme {
                SplashScreen()
            }
        }
    }

}