package com.theteampotato.gifit.domain.di

import android.content.Context
import com.theteampotato.gifit.domain.usecase.DownloadTranslationModel

import com.theteampotato.gifit.domain.usecase.ReadText
import com.theteampotato.gifit.domain.usecase.TranslateText
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
    fun providesGoogleMLKitTranslator() = GoogleMLKitTranslator()

    @Provides
    fun providesDownloadTranslationModel(
        @ApplicationContext context: Context,
        translator: GoogleMLKitTranslator
    ) = DownloadTranslationModel(context, translator)

    @Provides
    fun providesTranslateText(
        @ApplicationContext context: Context,
        translator: GoogleMLKitTranslator
    ) = TranslateText(context, translator)

    @Provides
    fun providesReadText(@ApplicationContext context: Context) = ReadText(context)

}