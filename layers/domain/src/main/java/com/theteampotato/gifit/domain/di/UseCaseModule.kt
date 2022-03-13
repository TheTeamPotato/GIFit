package com.theteampotato.gifit.domain.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences

import com.theteampotato.gifit.domain.usecase.DownloadTranslationModel
import com.theteampotato.gifit.domain.usecase.ReadText
import com.theteampotato.gifit.domain.usecase.TranslateText
import com.theteampotato.gifit.translate.GoogleMLKitTranslator

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGoogleMLKitTranslator() = GoogleMLKitTranslator()

    @Provides
    @Singleton
    fun providesDownloadTranslationModel(
        @ApplicationContext context: Context,
        translator: GoogleMLKitTranslator
    ) = DownloadTranslationModel(context, translator)

    @Provides
    @Singleton
    fun providesTranslateText(
        @ApplicationContext context: Context,
        dataStore: DataStore<Preferences>,
        translator: GoogleMLKitTranslator
    ) = TranslateText(context, dataStore, translator)

    @Provides
    fun providesReadText(@ApplicationContext context: Context) = ReadText(context)

}