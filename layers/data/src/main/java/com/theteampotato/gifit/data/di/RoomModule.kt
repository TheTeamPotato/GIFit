package com.theteampotato.gifit.data.di

import android.content.Context
import com.theteampotato.gifit.data.local.database.GIFitRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = GIFitRoomDatabase.getDatabase(context)

    @Provides
    fun providesSearchResultDao(database: GIFitRoomDatabase) = database.searchResultDao()

}

