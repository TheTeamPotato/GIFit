package com.theteampotato.gifit.domain.di

import android.content.Context

import com.theteampotato.gifit.data.remote.GiphyService
import com.theteampotato.gifit.domain.usecase.GetSearchResult
import com.theteampotato.gifit.translate.GoogleMLKitTranslator

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGoogleMLKitTranslator() : GoogleMLKitTranslator = GoogleMLKitTranslator()

    @Provides
    fun providesGetSearchResult(
        @ApplicationContext context: Context,
        translator: GoogleMLKitTranslator,
        giphyService: GiphyService
    ) = GetSearchResult(context, translator, giphyService)

}