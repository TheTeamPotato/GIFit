package com.theteampotato.gifit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchResult")
data class SearchResultEntity constructor(
    var searchString: String,
    var resultGiftUrl: String,
    var isFavorite: Boolean,
    var translatedValue: String,
    var searchType: SearchType,
    var showInHistory: Boolean,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}