package com.theteampotato.gifit

import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

import com.theteampotato.gifit.home.view.HomeScreen
import com.theteampotato.gifit.splash.view.SplashScreen
import com.theteampotato.gifit.ui.GIFitTheme

import dagger.hilt.android.AndroidEntryPoint

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    private var parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFitTheme {
                //SplashScreen()
                HomeScreen()
            }
        }
    }

}