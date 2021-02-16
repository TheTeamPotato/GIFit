package com.theteampotato.gifit.data.local.datasource

import androidx.lifecycle.LiveData

import com.theteampotato.gifit.data.local.dao.SearchResultDao
import com.theteampotato.gifit.data.local.entity.SearchResult

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val searchResultDao: SearchResultDao
) : ILocalDataSource<SearchResult> {

    override suspend fun insert(searchResult: SearchResult) {
        searchResultDao.insert(searchResult)
    }

    override suspend fun insertAll(resultList: List<SearchResult>) {
        searchResultDao.insertAll(resultList)
    }

    override suspend fun update(searchResult: SearchResult) {
        searchResultDao.update(searchResult)
    }

    override suspend fun delete(searchResult: SearchResult) {
        searchResultDao.delete(searchResult)
    }

    override suspend fun get(id: Int): LiveData<SearchResult> {
        return searchResultDao.getResultById(id)
    }

    override suspend fun getAll(): LiveData<List<SearchResult>> {
        return searchResultDao.getAll()
    }

    suspend fun getFavoritesResultById(id: Int): LiveData<SearchResult> {
        return searchResultDao.getFavoritesResultById(id)
    }

    suspend fun getAllFavoritesResults(): LiveData<List<SearchResult>> {
        return searchResultDao.getAllFavoritesResults()
    }

}