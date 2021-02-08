package com.theteampotato.gifit

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent

import com.theteampotato.gifit.splash.view.SplashScreen
import com.theteampotato.gifit.ui.GIFitTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFitTheme {
                SplashScreen()
            }
        }
    }
}