package com.theteampotato.gifit

import androidx.multidex.MultiDexApplication

import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class GIFitApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }
}