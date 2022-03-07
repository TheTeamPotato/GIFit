package com.theteampotato.gifit.data.di

import com.theteampotato.gifit.data.local.datasource.LocalDataSource
import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import com.theteampotato.gifit.data.remote.GiphyService
import com.theteampotato.gifit.data.remote.repository.GIFitRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

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

}