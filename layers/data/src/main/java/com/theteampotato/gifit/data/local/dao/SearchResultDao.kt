package com.theteampotato.gifit.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchResultDao : BaseDao<SearchResultEntity> {

    @Query("SELECT * FROM SearchResult")
    fun getAll(): LiveData<List<SearchResultEntity>>

    @Query("SELECT * FROM SearchResult WHERE id = :id")
    fun getResultById(id: Int): LiveData<SearchResultEntity>

    @Query("SELECT * FROM SearchResult WHERE id = :id and isFavorite = 1")
    fun getFavoritesResultById(id: Int): LiveData<SearchResultEntity>

    @Query("SELECT * FROM SearchResult WHERE isFavorite = 1")
    fun getAllFavoritesResults(): LiveData<List<SearchResultEntity>>

    @Query("UPDATE SearchResult SET isFavorite = 1 WHERE id = :id")
    fun favoriteLastSearchResult(id: Long)

    @Query("UPDATE SearchResult SET isFavorite = 0 WHERE id = :id")
    fun removeSearchResultFromFavorites(id: Long)

    @Query("SELECT * FROM SearchResult WHERE searchText LIKE :searchText")
    fun getSearchResultEntry(searchText: String) : Flow<SearchResultEntity?>

}