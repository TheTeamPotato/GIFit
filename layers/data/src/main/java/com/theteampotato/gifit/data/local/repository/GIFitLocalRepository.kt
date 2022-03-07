package com.theteampotato.gifit.data.local.repository

import androidx.lifecycle.LiveData
import com.theteampotato.gifit.data.local.datasource.LocalDataSource
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import javax.inject.Inject

class GIFitLocalRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : BaseLocalRepository<SearchResultEntity> {

    override suspend fun insert(SearchResultEntity: SearchResultEntity) = localDataSource.insert(SearchResultEntity)

    override suspend fun insertAll(SearchResultEntityList: List<SearchResultEntity>) {
        localDataSource.insertAll(SearchResultEntityList)
    }

    override suspend fun update(SearchResultEntity: SearchResultEntity) {
        localDataSource.update(SearchResultEntity)
    }

    override suspend fun delete(SearchResultEntity: SearchResultEntity) {
        localDataSource.delete(SearchResultEntity)
    }

    override suspend fun get(id: Int): LiveData<SearchResultEntity> {
        return localDataSource.get(id)
    }

    override suspend fun getAll(): LiveData<List<SearchResultEntity>> {
        return localDataSource.getAll()
    }

    fun getFavoritesResultById(id: Int): LiveData<SearchResultEntity> {
        return localDataSource.getFavoritesResultById(id)
    }

    fun getAllFavoritesResults(): LiveData<List<SearchResultEntity>> {
        return localDataSource.getAllFavoritesResults()
    }

    fun favoriteLastSearchResult(id: Long) {
        localDataSource.favoriteLastSearchResult(id)
    }

    fun removeSearchResultFromFavorites(id: Long) {
        localDataSource.removeSearchResultFromFavorites(id)
    }

    fun getSearchResultEntry(searchText: String) = localDataSource.getSearchResultEntry(searchText)

}