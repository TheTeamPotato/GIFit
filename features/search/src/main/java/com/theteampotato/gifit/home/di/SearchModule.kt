package com.theteampotato.gifit.home.di

import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Singleton
    @Provides
    fun providesDispatcherProvider() = DispatcherProvider(
        ui = Dispatchers.Main,
        io = Dispatchers.IO,
        default = Dispatchers.Default
    )

}