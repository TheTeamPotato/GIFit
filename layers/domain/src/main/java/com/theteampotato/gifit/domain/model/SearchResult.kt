package com.theteampotato.gifit.domain.model

import com.theteampotato.gifit.data.remote.model.GiphyResult

data class SearchResult(
    var translatedText: String,
    var giphyResult: GiphyResult
)
