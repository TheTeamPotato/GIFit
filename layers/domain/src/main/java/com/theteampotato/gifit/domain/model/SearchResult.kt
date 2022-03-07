package com.theteampotato.gifit.domain.model

data class SearchResult(
    var id: Long? = null,
    var searchText: String? = null,
    var translatedText: String,
    var gifURL: String,
    var isFavorite: Boolean = false,
    var showInHistory: Boolean = false
)
