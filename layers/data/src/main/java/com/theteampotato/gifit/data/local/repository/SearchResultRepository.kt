package com.theteampotato.gifit.data.local.repository

import androidx.lifecycle.LiveData
import com.theteampotato.gifit.data.local.datasource.LocalDataSource
import com.theteampotato.gifit.data.local.entity.SearchResult
import javax.inject.Inject

class SearchResultRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : BaseRepository<SearchResult> {

    override suspend fun insert(searchResult: SearchResult) {
        localDataSource.insert(searchResult)
    }

    override suspend fun insertAll(searchResultList: List<SearchResult>) {
        localDataSource.insertAll(searchResultList)
    }

    override suspend fun update(searchResult: SearchResult) {
        localDataSource.update(searchResult)
    }

    override suspend fun delete(searchResult: SearchResult) {
        localDataSource.delete(searchResult)
    }

    override suspend fun get(id: Int): LiveData<SearchResult> {
        return localDataSource.get(id)
    }

    override suspend fun getAll(): LiveData<List<SearchResult>> {
        return localDataSource.getAll()
    }

    suspend fun getFavoritesResultById(id: Int): LiveData<SearchResult> {
        return localDataSource.getFavoritesResultById(id)
    }

    suspend fun getAllFavoritesResults(): LiveData<List<SearchResult>> {
        return localDataSource.getAllFavoritesResults()
    }

}