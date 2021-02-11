package com.theteampotato.gifit.data.remote

import com.theteampotato.gifit.data.model.GiphyResult
import javax.inject.Inject

private const val BASE_URL = "https://api.giphy.com/v1/gifs"
private const val API_KEY = "lrTzjFGxlhO6YzuxF7djazRM6gEh4gml"
private const val LIMIT = "5"

class GiphyService @Inject constructor() : Service(BASE_URL) {

    suspend fun search(keyword: String) : GiphyResult =
        "/search?api_key=$API_KEY&q=$keyword&limit=$LIMIT&offset=5&rating=g&lang=en".execute()
}