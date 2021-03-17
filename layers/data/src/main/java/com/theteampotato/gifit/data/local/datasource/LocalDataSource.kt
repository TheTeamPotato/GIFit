package com.theteampotato.gifit.data.local.datasource

import androidx.lifecycle.LiveData

import com.theteampotato.gifit.data.local.dao.SearchResultDao
import com.theteampotato.gifit.data.local.entity.SearchResultEntity

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val searchResultDao: SearchResultDao
) : ILocalDataSource<SearchResultEntity> {

    override suspend fun insert(searchResultEntity: SearchResultEntity) {
        searchResultDao.insert(searchResultEntity)
    }

    override suspend fun insertAll(resultEntityList: List<SearchResultEntity>) {
        searchResultDao.insertAll(resultEntityList)
    }

    override suspend fun update(searchResultEntity: SearchResultEntity) {
        searchResultDao.update(searchResultEntity)
    }

    override suspend fun delete(searchResultEntity: SearchResultEntity) {
        searchResultDao.delete(searchResultEntity)
    }

    override suspend fun get(id: Int): LiveData<SearchResultEntity> {
        return searchResultDao.getResultById(id)
    }

    override suspend fun getAll(): LiveData<List<SearchResultEntity>> {
        return searchResultDao.getAll()
    }

    suspend fun getFavoritesResultById(id: Int): LiveData<SearchResultEntity> {
        return searchResultDao.getFavoritesResultById(id)
    }

    suspend fun getAllFavoritesResults(): LiveData<List<SearchResultEntity>> {
        return searchResultDao.getAllFavoritesResults()
    }

}