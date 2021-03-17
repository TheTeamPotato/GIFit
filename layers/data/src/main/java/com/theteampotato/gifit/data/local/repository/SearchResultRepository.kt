package com.theteampotato.gifit.data.local.repository

import androidx.lifecycle.LiveData
import com.theteampotato.gifit.data.local.datasource.LocalDataSource
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import javax.inject.Inject

class SearchResultRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : BaseRepository<SearchResultEntity> {

    override suspend fun insert(searchResultEntity: SearchResultEntity) {
        localDataSource.insert(searchResultEntity)
    }

    override suspend fun insertAll(searchResultEntityList: List<SearchResultEntity>) {
        localDataSource.insertAll(searchResultEntityList)
    }

    override suspend fun update(searchResultEntity: SearchResultEntity) {
        localDataSource.update(searchResultEntity)
    }

    override suspend fun delete(searchResultEntity: SearchResultEntity) {
        localDataSource.delete(searchResultEntity)
    }

    override suspend fun get(id: Int): LiveData<SearchResultEntity> {
        return localDataSource.get(id)
    }

    override suspend fun getAll(): LiveData<List<SearchResultEntity>> {
        return localDataSource.getAll()
    }

    suspend fun getFavoritesResultById(id: Int): LiveData<SearchResultEntity> {
        return localDataSource.getFavoritesResultById(id)
    }

    suspend fun getAllFavoritesResults(): LiveData<List<SearchResultEntity>> {
        return localDataSource.getAllFavoritesResults()
    }

}