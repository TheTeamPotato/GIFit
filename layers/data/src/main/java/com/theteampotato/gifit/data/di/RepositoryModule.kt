package com.theteampotato.gifit.data.di

import android.content.Context
import com.theteampotato.gifit.data.datastore.preferencesDataStore
import com.theteampotato.gifit.data.local.datasource.LocalDataSource
import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import com.theteampotato.gifit.data.remote.GiphyService
import com.theteampotato.gifit.data.remote.repository.GIFitRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
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

}