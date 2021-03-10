package com.theteampotato.gifit.home.di

import com.theteampotato.gifit.home.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun providesDispatcherProvider() = DispatcherProvider(
        ui = Dispatchers.Main,
        io = Dispatchers.IO,
        default = Dispatchers.Default
    )

}