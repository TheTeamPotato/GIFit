package com.theteampotato.gifit.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

import com.theteampotato.gifit.data.local.entity.SearchResult

@Dao
interface SearchResultDao : BaseDao<SearchResult> {

    @Query("SELECT * FROM SearchResult")
    fun getAll(): LiveData<List<SearchResult>>

    @Query("SELECT * FROM SearchResult WHERE id = :id")
    fun getResultById(id: Int): LiveData<SearchResult>

    @Query("SELECT * FROM SearchResult WHERE id = :id and isFavorite = 1")
    fun getFavoritesResultById(id: Int): LiveData<SearchResult>

    @Query("SELECT * FROM SearchResult WHERE isFavorite = 1")
    fun getAllFavoritesResults(): LiveData<List<SearchResult>>

}