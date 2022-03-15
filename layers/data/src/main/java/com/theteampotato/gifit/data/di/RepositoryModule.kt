package com.theteampotato.gifit.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.theteampotato.gifit.data.LocaleManager
import com.theteampotato.gifit.data.datastore.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /*@Provides
    @ViewModelScoped
    fun provideGIFitLocalRepository(localDataSource: LocalDataSource): GIFitLocalRepository {
        return GIFitLocalRepository(localDataSource)
    }*/

//    @Provides
//    @ViewModelScoped
//    fun provideGIFitRemoteRepository(giphyService: GiphyService) = GIFitRemoteRepository(giphyService)

    @Provides
    @Singleton
    fun providesPreferencesDataStore(@ApplicationContext context: Context) =
        context.preferencesDataStore

    @Provides
    @Singleton
    fun providesLocaleManager(
        @ApplicationContext context: Context,
        preferencesDataStore: DataStore<Preferences>
    ) = LocaleManager(context, preferencesDataStore)

}