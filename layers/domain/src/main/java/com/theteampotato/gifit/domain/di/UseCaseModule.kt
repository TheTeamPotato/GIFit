package com.theteampotato.gifit.domain.di

import com.theteampotato.gifit.translate.GoogleMLKitTranslator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGoogleMLKitTranslator() : GoogleMLKitTranslator = GoogleMLKitTranslator()

}