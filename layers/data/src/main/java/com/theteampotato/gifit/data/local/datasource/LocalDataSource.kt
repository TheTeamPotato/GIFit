package com.theteampotato.gifit.data.local.datasource

import androidx.lifecycle.LiveData

import com.theteampotato.gifit.data.local.dao.SearchResultDao
import com.theteampotato.gifit.data.local.entity.SearchResultEntity

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val searchResultDao: SearchResultDao
) : ILocalDataSource<SearchResultEntity> {

    override suspend fun insert(SearchResultEntity: SearchResultEntity) = searchResultDao.insert(SearchResultEntity)

    override suspend fun insertAll(resultListEntity: List<SearchResultEntity>) {
        searchResultDao.insertAll(resultListEntity)
    }

    override suspend fun update(SearchResultEntity: SearchResultEntity) {
        searchResultDao.update(SearchResultEntity)
    }

    override suspend fun delete(SearchResultEntity: SearchResultEntity) {
        searchResultDao.delete(SearchResultEntity)
    }

    override suspend fun get(id: Int): LiveData<SearchResultEntity> {
        return searchResultDao.getResultById(id)
    }

    override suspend fun getAll(): LiveData<List<SearchResultEntity>> {
        return searchResultDao.getAll()
    }

    fun getFavoritesResultById(id: Int): LiveData<SearchResultEntity> {
        return searchResultDao.getFavoritesResultById(id)
    }

    fun getAllFavoritesResults(): LiveData<List<SearchResultEntity>> {
        return searchResultDao.getAllFavoritesResults()
    }

    fun favoriteLastSearchResult(id: Long) {
        searchResultDao.favoriteLastSearchResult(id)
    }

    fun removeSearchResultFromFavorites(id: Long) {
        searchResultDao.removeSearchResultFromFavorites(id)
    }

    fun getSearchResultEntry(searchText: String) = searchResultDao.getSearchResultEntry(searchText)

}