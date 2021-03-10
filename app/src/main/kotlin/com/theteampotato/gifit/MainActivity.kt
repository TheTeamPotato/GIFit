package com.theteampotato.gifit

import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.theteampotato.gifit.home.view.HomeScreen
import com.theteampotato.gifit.home.viewmodel.HomeViewModel
import com.theteampotato.gifit.ui.GIFitTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GIFitTheme {
                //SplashScreen()
                HomeScreen(viewModel = homeViewModel)
            }
        }
    }

}