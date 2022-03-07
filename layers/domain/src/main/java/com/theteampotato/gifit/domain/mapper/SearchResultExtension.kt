package com.theteampotato.gifit.domain.mapper

import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.remote.model.GiphyResponse
import com.theteampotato.gifit.domain.model.SearchResult

fun GiphyResponse.toSearchResult(translatedText: String) = SearchResult(
    translatedText = translatedText,
    gifURL = data.first().images.original.url
)

fun SearchResult.toSearchResultEntity(searchString: String, resultGifUrl: String, isFavorite: Boolean) = SearchResultEntity(
    searchText = searchString,
    gifUrl = resultGifUrl,
    isFavorite = isFavorite,
    translatedText = this.translatedText,
    showInHistory = true
)

fun SearchResultEntity.toSearchResult() = SearchResult(
    id = this.id,
    searchText = this.searchText,
    translatedText = this.translatedText,
    gifURL = this.gifUrl,
    isFavorite = this.isFavorite,
    showInHistory = this.showInHistory
)