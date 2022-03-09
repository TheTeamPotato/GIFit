package com.theteampotato.gifit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchResult")
data class SearchResultEntity constructor(
    var searchText: String,
    var gifUrl: String,
    var isFavorite: Boolean,
    var translatedText: String,
    var showInHistory: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}